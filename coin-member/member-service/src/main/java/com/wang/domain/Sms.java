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

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/10 14:48
 * @Version 1.0
 */
/**
    * 短信信息
    */
@ApiModel(value="com-wang-domain-Sms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms")
public class Sms {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键id")
    private Long id;

    /**
     * 短信模板ID
     */
    @TableField(value = "template_code")
    @ApiModelProperty(value="短信模板ID")
    private String templateCode;

    /**
     * 国际区号
     */
    @TableField(value = "country_code")
    @ApiModelProperty(value="国际区号")
    private String countryCode;

    /**
     * 短信接收手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value="短信接收手机号")
    private String mobile;

    /**
     * 短信内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="短信内容")
    private String content;

    /**
     * 短信状态：0，默认值；大于0，成功发送短信数量；小于0，异常；
     */
    @TableField(value = "status")
    @ApiModelProperty(value="短信状态：0，默认值；大于0，成功发送短信数量；小于0，异常；")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 发送时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value="发送时间")
    private Date lastUpdateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value="创建时间")
    private Date created;
}