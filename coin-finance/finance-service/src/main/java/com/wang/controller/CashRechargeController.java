package com.wang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.domain.CashRecharge;
import com.wang.domain.CashRechargeAuditRecord;
import com.wang.model.CashParam;
import com.wang.model.R;
import com.wang.service.CashRechargeService;
import com.wang.util.ReportCsvUtils;
import com.wang.vo.CashTradeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;
import springfox.documentation.annotations.ApiIgnore;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author wangzhen
 * @Description 资金交易Controller
 * @Date 2025/4/17 21:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/cashRecharges")
@Api(value = "GCN控制器")
public class CashRechargeController {
    @Autowired
    private CashRechargeService cashRechargeService;

    @GetMapping("/records")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示的条数"),
            @ApiImplicitParam(name = "coinId", value = "当前页"),
            @ApiImplicitParam(name = "userId", value = "用户的ID"),
            @ApiImplicitParam(name = "userName", value = "用户的名称"),
            @ApiImplicitParam(name = "mobile", value = "用户的手机号"),
            @ApiImplicitParam(name = "status", value = "充值的状态"),
            @ApiImplicitParam(name = "numMin", value = "充值金额的最小值"),
            @ApiImplicitParam(name = "numMax", value = "充值金额的最小值"),
            @ApiImplicitParam(name = "startTime", value = "充值开始时间"),
            @ApiImplicitParam(name = "endTime", value = "充值结束时间"),
    })
    public R<Page<CashRecharge>> findByPage(
            @ApiIgnore Page<CashRecharge> page, Long coinId,
            Long userId, String userName, String mobile,
            Byte status, String numMin, String numMax,
            String startTime, String endTime
    ) {
        Page<CashRecharge> pageData = cashRechargeService.findByPage(page, coinId, userId, userName,
                mobile, status, numMin, numMax, startTime, endTime);
        return R.ok(pageData);
    }

//finance/cashRecharges/records/export cash_recharge_audit_export
//         String[] header = {"ID","用户ID", "用户名", "真实用户名", "充值币种", "充值金额(USDT)", "手续费", "到账金额(CNY)", "充值方式", "充值订单", "参考号", "充值时间","完成时间", "状态","审核备注","审核级数"};
//        String[] properties = {"id","userId", "username", "realName", "coinName", "num", "fee", "mum", "type", "tradeno", "remark", "created", "lastTime","statusStr","auditRemark","step"};

    /**
     * csv文件的导出，主要分为三步：
     *    1) 按照分页的参数，先查询出所有的分页数据
     *    2) 对于在导出时需要特殊处理的列(如日期的格式需要格式化)，定义该列的Processor，在processot中执行处理逻辑
     *    3) 使用ReportCsvUtils导出数据
     */
    @GetMapping("/export") // TODO 路径修改
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coinId", value = "当前页"),
            @ApiImplicitParam(name = "userId", value = "用户的ID"),
            @ApiImplicitParam(name = "userName", value = "用户的名称"),
            @ApiImplicitParam(name = "mobile", value = "用户的手机号"),
            @ApiImplicitParam(name = "status", value = "充值的状态"),
            @ApiImplicitParam(name = "numMin", value = "充值金额的最小值"),
            @ApiImplicitParam(name = "numMax", value = "充值金额的最小值"),
            @ApiImplicitParam(name = "startTime", value = "充值开始时间"),
            @ApiImplicitParam(name = "endTime", value = "充值结束时间"),
    })
    public void recordsExport(Long coinId,
                              Long userId, String userName, String mobile,
                              Byte status, String numMin, String numMax,
                              String startTime, String endTime) {
        // 封装Page对象
        Page<CashRecharge> page = new Page<>(1, 10000);
        Page<CashRecharge> pageData = cashRechargeService.findByPage(page, coinId, userId, userName,
                mobile, status, numMin, numMax, startTime, endTime);
        // 查询分页数据
        List<CashRecharge> records = pageData.getRecords();
        if (!CollectionUtils.isEmpty(records)) {// 分页数据不为空才操作
            String[] header = {"ID", "用户ID", "用户名", "真实用户名", "充值币种", "充值金额(USDT)", "手续费", "到账金额(CNY)", "充值方式", "充值订单", "参考号", "充值时间", "完成时间", "状态", "审核备注", "审核级数"};
            String[] properties = {"id", "userId", "username", "realName", "coinName", "num", "fee", "mum", "type", "tradeno", "remark", "created", "lastTime", "status", "auditRemark", "step"};

            CellProcessorAdaptor longToStringAdapter = new CellProcessorAdaptor() {
                @Override
                public <T> T execute(Object o, CsvContext csvContext) {
                    return (T) String.valueOf(o);
                }
            };

            // 对于金额,需要8位有效数字：new一个金额的Adaptor格式化
            DecimalFormat decimalFormat = new DecimalFormat("0.00000000");
            CellProcessorAdaptor moneyCellProcessorAdaptor = new CellProcessorAdaptor() {
                @Override
                public <T> T execute(Object o, CsvContext csvContext) {
                    BigDecimal num = (BigDecimal) o;
                    String numReal = decimalFormat.format(num);
                    return (T) numReal;
                }
            };

            // @ApiModelProperty(value = "类型：alipay，支付宝；cai1pay，财易付；bank，银联；")
            // 支付类型的Adaptor:中英转换：alipay -> 支付宝
            CellProcessorAdaptor typeAdapter = new CellProcessorAdaptor() {
                @Override
                public <T> T execute(Object o, CsvContext csvContext) {
                    String type = String.valueOf(o);
                    String typeName = "";
                    switch (type) {
                        case "alipay":
                            typeName = "支付宝";
                            break;
                        case "cai1pay":
                            typeName = "财易付";
                            break;
                        case "bank":
                            typeName = "银联";
                            break;
                        case "linepay":
                            typeName = "在线支付";
                            break;
                        default:
                            typeName = "未知";
                            break;

                    }
                    return (T) typeName;
                }
            };

            // 日期格式化的Adaptor
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CellProcessorAdaptor timeCellProcessorAdaptor = new CellProcessorAdaptor() {
                @Override
                public <T> T execute(Object o, CsvContext csvContext) {
                    if(o ==null){
                        return (T)"" ;
                    }
                    Date date = (Date) o;
                    String dateStr = simpleDateFormat.format(date);
                    return (T) dateStr;
                }
            };

            // 0-待审核；1-审核通过；2-拒绝；3-充值成功
            // 审核状态的Adaptor
            CellProcessorAdaptor statusCellProcessorAdaptor =  new CellProcessorAdaptor(){
                @Override
                public <T> T execute(Object o, CsvContext csvContext) {
                    Integer status = Integer.valueOf(String.valueOf(o));
                    String statusStr = "" ;
                    switch (status){
                        case 0:
                            statusStr = "待审核" ;
                            break;
                        case 1:
                            statusStr = "审核通过" ;
                            break;
                        case 2:
                            statusStr = "拒绝" ;
                            break;
                        case 3:
                            statusStr = "充值成功" ;
                            break;
                        default:
                            statusStr = "未知" ;
                            break;

                    }
                    return (T) statusStr;
                }
            };

            // 对与需要特殊处理的列，在processor中定义处理逻辑；并将每个列的processor封装到一个数组中
            CellProcessor[] PROCESSOR = new CellProcessor[]{
                    longToStringAdapter,  longToStringAdapter, null, null, null, // "ID", "用户ID", "用户名"不需要, "真实用户名"不需要, "充值币种"不需要,
                    moneyCellProcessorAdaptor, moneyCellProcessorAdaptor, moneyCellProcessorAdaptor, typeAdapter,  // "充值金额(USDT)", "手续费", "到账金额(CNY)", 充值方式"
                    null, null ,timeCellProcessorAdaptor , timeCellProcessorAdaptor ,//充值订单"不需要, "参考号"不需要, "充值时间", "完成时间
                    statusCellProcessorAdaptor,   null ,null //状态 "审核备注"不需要, "审核级数"不需要

            };

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            try {
                // 按照对应的参数导出csv文件
                // header是csv文件中第一行列名，Properties是每一列对应的数据，文件名是导出的文件名，records是数据，PROCESSOR做自定义的数据处理
                ReportCsvUtils.reportListCsv(requestAttributes.getResponse(), header, properties, "场外交易充值审核.csv", records, PROCESSOR);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @GetMapping("/user/records")
    @ApiOperation(value = "查询当前用户的充值记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示的大小"),
            @ApiImplicitParam(name = "status", value = "充值的状态"),
    })
    public R<Page<CashRecharge>> findUserCashRecharge(@ApiIgnore Page<CashRecharge> page, Byte status) {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Page<CashRecharge> cashRechargePage = cashRechargeService.findUserCashRecharge(page, userId, status);
        return R.ok(cashRechargePage);
    }


//    @PostMapping("/buy")
//    @ApiOperation(value = "GCN的充值操作")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "cashParam", value = "现金交易的参数")
//    })
//    public R<CashTradeVo> buy(@RequestBody @Validated CashParam cashParam) {
//        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//        CashTradeVo cashTradeVo = cashRechargeService.buy(userId, cashParam);
//        return R.ok(cashTradeVo);
//    }



//    @ApiOperation(value = "现金的充值审核")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "cashRechargeAuditRecord" ,value = "现金的充值审核")
//    })
//    @PostMapping("/cashRechargeUpdateStatus")
//    public R cashRechargeUpdateStatus(@RequestBody  CashRechargeAuditRecord cashRechargeAuditRecord){
//        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//        boolean isOk =  cashRechargeService.cashRechargeAudit(userId,cashRechargeAuditRecord) ;
//        return isOk ? R.ok():R.fail("审核失败") ;
//    }
}
