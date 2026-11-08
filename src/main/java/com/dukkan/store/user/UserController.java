package com.dukkan.store.user;

import com.dukkan.store.system.ApiResponse;
import com.dukkan.store.system.ResponseMessage;
import com.dukkan.store.system.StatusCode;
import com.dukkan.store.user.dto.UserDto;
import com.dukkan.store.user.dto.UserRequest;
import com.dukkan.store.user.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping
    public ApiResponse getAllUsers(){
        List<User> users = userService.getAllUsers();

        List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .toList();

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.Find_All_Success,
                userDtos
        );
    }
    @PostMapping
    public ApiResponse saveUser(@RequestBody @Valid UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);

        User savedUser = userService.save(user);

        UserDto userDto = userMapper.toDto(savedUser);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.User_Saved,
                userDto
        );
    }
    @GetMapping("/user")
    public ApiResponse findUserByEmail(@RequestParam(required = true) String email){
        User user = userService.findByEmail(email);

        UserDto userDto = userMapper.toDto(user);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.User_Found,
                userDto
        );
    }
    @GetMapping("/user/{id}")
    public ApiResponse findUserById(@PathVariable Integer id){
        User user = userService.findById(id);

        UserDto userDto = userMapper.toDto(user);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.User_Found,
                userDto
        );
    }

    @PutMapping("/user/{id}")
    public ApiResponse updateUserById(@PathVariable Integer id, @RequestBody @Valid UserDto userUpdate){
        User user = userMapper.toEntity(userUpdate);
        User updatedUser = userService.update(id, user);
        UserDto userDto = userMapper.toDto(updatedUser);

        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.User_Updated,
                userDto
        );
    }
    @DeleteMapping("/user/{id}")
    public ApiResponse deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.Delete_Success
        );
    }

    @PutMapping("/activate/{userId}")
    public ApiResponse updateActivationStatus(@PathVariable Integer userId,@RequestParam(required = true) boolean isActivate){
        User user = userService.updateActivation(userId, isActivate);
        UserDto userDto = userMapper.toDto(user);
        return new ApiResponse(
                true,
                StatusCode.SUCCESS,
                ResponseMessage.User_Activation_Status_Changed_Successfully,
                userDto
        );
    }



}
