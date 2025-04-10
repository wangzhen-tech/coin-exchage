package com.wang.vo;

import com.wang.domain.User;
import com.wang.domain.UserAuthAuditRecord;
import com.wang.domain.UserAuthInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
/**
 * @Author wangzhen
 * @Description 后台管理系统-用户中心-会员管理-高级实名认证，该处的信息需要三部分内容组合，封装为一个VO对象
 * @Date 2025/4/10 19:17
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "用户认证的详细信息")
public class UseAuthInfoVo  implements Serializable{
    @ApiModelProperty(value = "用户")
    private User user ;

    @ApiModelProperty(value = "用户认证的详情列表")
    private List<UserAuthInfo> userAuthInfoList ;

    @ApiModelProperty(value = "用户审核历史")
    private List<UserAuthAuditRecord> authAuditRecordList ;
}
