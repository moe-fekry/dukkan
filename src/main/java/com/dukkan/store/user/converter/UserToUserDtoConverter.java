package com.dukkan.store.user.converter;

import com.dukkan.store.user.User;
import com.dukkan.store.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto(
                source.getId(),
                source.getName(),
                source.getEmail(),
                source.getAddress(),
                source.getPhoneNumber(),
                source.getRoles(),
                source.isActivate()
        );
        return userDto;
    }
}
