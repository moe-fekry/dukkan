package com.dukkan.store.user.mapper;

import com.dukkan.store.user.User;
import com.dukkan.store.user.dto.UserDto;
import com.dukkan.store.user.dto.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);
}
