package com.wang.mappers;

import com.wang.domain.User;
import com.wang.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T21:45:43+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_411 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto convert2Dto(User source) {
        if ( source == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public User convert2Entity(UserDto source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public List<UserDto> convert2Dto(List<User> source) {
        if ( source == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( source.size() );
        for ( User user : source ) {
            list.add( convert2Dto( user ) );
        }

        return list;
    }

    @Override
    public List<User> convert2Entity(List<UserDto> source) {
        if ( source == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( source.size() );
        for ( UserDto userDto : source ) {
            list.add( convert2Entity( userDto ) );
        }

        return list;
    }
}
