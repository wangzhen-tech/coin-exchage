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

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author wangzhen
 * @Description ${description}
 * @Date 2025/4/3 16:15
 * @Version 1.0
 */
/**
    * 角色
    */
@ApiModel(value="com-wang-domain-SysRole")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Long id;

    // -------------------- 主体 begin----------------------
    /**
     * 名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="名称")
    @NotNull // 在使用到的地方通过@Validated启用校验
    private String name;

    /**
     * 代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value="代码")
    @NotNull
    private String code;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="描述")
    private String description;

    // -------------------- 主体 end----------------------

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill=FieldFill.INSERT)
    @ApiModelProperty(value="创建人")
    private Long createBy;

    /**
     * 修改人
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value="修改人")
    private Long modifyBy;

    /**
     * 状态0:禁用 1:启用
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态0:禁用 1:启用")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "created", fill=FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date created;

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time",fill=FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="修改时间")
    private Date lastUpdateTime;
}