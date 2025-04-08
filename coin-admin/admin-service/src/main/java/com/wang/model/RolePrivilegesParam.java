package com.wang.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Collections;
import java.util.List;
/**
 * @Author wangzhen
 * @Description 封装对角色授权时的前端传递的参数对象：角色id和权限id列表
 * @Date 2025/4/8 20:29
 * @Version 1.0
 */
@Data
@ApiModel(value = "接收角色和权限数据")
public class RolePrivilegesParam {
    @ApiModelProperty(value = "角色ID")
    private Long roleId  ;

    @ApiModelProperty(value = "角色包含的权限列表")
    private List<Long> privilegeIds =   Collections.emptyList();// 防止空指针
}
