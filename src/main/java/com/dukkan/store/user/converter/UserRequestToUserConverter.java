package com.dukkan.store.user.converter;

import com.dukkan.store.user.User;
import com.dukkan.store.user.dto.UserDto;
import com.dukkan.store.user.dto.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User> {
    @Override
    public User convert(UserRequest source) {
        User user =new User();
        user.setName(source.name());
        user.setEmail(source.email());
        user.setPassword(source.password());
        user.setRoles(source.roles());
        user.setAddress(source.address());
        user.setPhoneNumber(source.phoneNumber());
        user.setActivate(source.activate());
        return user;
    }
}
