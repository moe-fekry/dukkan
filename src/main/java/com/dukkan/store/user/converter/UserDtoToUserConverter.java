package com.dukkan.store.user.converter;

import com.dukkan.store.user.User;
import com.dukkan.store.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto source) {
        User user = new User();
        user.setEmail(source.email());
        user.setName(source.name());
        user.setPhoneNumber(source.phoneNumber());
        user.setAddress(source.address());
        user.setRoles(source.roles());
        user.setActivate(source.activate());
        return user;
    }
}
