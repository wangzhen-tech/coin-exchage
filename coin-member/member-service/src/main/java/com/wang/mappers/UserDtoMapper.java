package com.wang.mappers;

import com.wang.domain.User;
import com.wang.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * @Author wangzhen
 * @Description UserDto的MapStruct的Mapper
 *              用来做对象的映射转化: entity2Dto Dto2Entity
 * @Date 2025/4/16 16:31
 * @Version 1.0
 */
@Mapper(componentModel = "spring")// 生成一个Spring的注解，并将该对象放置到容器中
// 在编译之后，mapstruct会自动生成实现类对象实现该接口
public interface UserDtoMapper{
    // 获取该对象的实例
    UserDtoMapper INSTANCE =  Mappers.getMapper(UserDtoMapper.class) ;

    /**
     * 将entity转化为dto
     * @param source
     * @return
     */
    UserDto convert2Dto(User source) ;

    /**
     * 将dto对象转化为entity对象
     * @param source
     * @return
     */
    User convert2Entity(UserDto source) ;

    /**
     * 将entity转化为dto
     * @param source
     * @return
     */
    List<UserDto> convert2Dto(List<User> source) ;

    /**
     * 将dto对象转化为entity对象
     * @param source
     * @return
     */
    List<User> convert2Entity(List<UserDto> source) ;
}
