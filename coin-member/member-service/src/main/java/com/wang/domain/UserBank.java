package com.wang.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author wangzhen
 * @Description 用户人民币提现地址
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
@ApiModel(value="com-wang-domain-UserBank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_bank")
public class UserBank {
    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="自增id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 银行卡名称
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="银行卡名称")
    private String remark;

    /**
     * 开户人
     */
    @TableField(value = "real_name")
    @ApiModelProperty(value="开户人")
    @NotBlank
    private String realName;

    /**
     * 开户行
     */
    @TableField(value = "bank")
    @ApiModelProperty(value="开户行")
    @NotBlank
    private String bank;

    /**
     * 开户省
     */
    @TableField(value = "bank_prov")
    @ApiModelProperty(value="开户省")
    private String bankProv;

    /**
     * 开户市
     */
    @TableField(value = "bank_city")
    @ApiModelProperty(value="开户市")
    private String bankCity;

    /**
     * 开户地址
     */
    @TableField(value = "bank_addr")
    @ApiModelProperty(value="开户地址")
    private String bankAddr;

    /**
     * 开户账号
     */
    @TableField(value = "bank_card")
    @ApiModelProperty(value="开户账号")
    @NotBlank
    private String bankCard;

    /**
     * 状态：0，禁用；1，启用；
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态：0，禁用；1，启用；")
    private Byte status;

    /**
     * 更新时间
     */
    @TableField(value = "last_update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date created;


    @ApiModelProperty(value = "交易密码")
    @TableField(exist = false)
    @NotBlank
    private String payPassword ;
}