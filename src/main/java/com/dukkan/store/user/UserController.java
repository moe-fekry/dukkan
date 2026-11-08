package com.dukkan.store.user;

import com.dukkan.store.system.ApiResponse;
import com.dukkan.store.system.StatusCode;
import com.dukkan.store.user.converter.UserDtoToUserConverter;
import com.dukkan.store.user.converter.UserRequestToUserConverter;
import com.dukkan.store.user.converter.UserToUserDtoConverter;
import com.dukkan.store.user.dto.UserDto;
import com.dukkan.store.user.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserToUserDtoConverter userToUserDtoConverter;
    private final UserRequestToUserConverter userRequestToUserConverter;
    private final UserDtoToUserConverter userDtoToUserConverter;


    @GetMapping
    public ApiResponse getAllUsers(){
        List<User> users = userService.getAllUsers();

        List<UserDto> userDtos = users.stream()
                .map(userToUserDtoConverter::convert)
                .toList();

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "Find All Success",
                userDtos
        );
    }
    @PostMapping
    public ApiResponse saveUser(@RequestBody @Valid UserRequest userRequest){
        User user = userRequestToUserConverter.convert(userRequest);

        User savedUser = userService.save(user);

        UserDto userDto = userToUserDtoConverter.convert(savedUser);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "User Saved",
                userDto
        );
    }
    @GetMapping("/user")
    public ApiResponse findUserByEmail(@RequestParam(required = true) String email){
        User user = userService.findByEmail(email);

        UserDto userDto = userToUserDtoConverter.convert(user);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "User Found",
                userDto
        );
    }
    @GetMapping("/user/{id}")
    public ApiResponse findUserById(@PathVariable Integer id){
        User user = userService.findById(id);

        UserDto userDto = userToUserDtoConverter.convert(user);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "User Found",
                userDto
        );
    }

    @PutMapping("/user/{id}")
    public ApiResponse updateUserById(@PathVariable Integer id, @RequestBody @Valid UserDto userUpdate){
        User user = userDtoToUserConverter.convert(userUpdate);
        User updatedUser = userService.update(id, user);
        UserDto userDto = userToUserDtoConverter.convert(updatedUser);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "User Updated",
                userDto
        );
    }
    @DeleteMapping("/user/{id}")
    public ApiResponse deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "Delete Success"
        );
    }

    @PutMapping("/activate/{userId}")
    public ApiResponse updateActivationStatus(@PathVariable Integer userId,@RequestParam(required = true) boolean isActivate){
        User user = userService.updateActivation(userId, isActivate);
        UserDto userDto = userToUserDtoConverter.convert(user);
        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                "User Activation Status Changed Sucessfully",
                userDto
        );
    }



}
