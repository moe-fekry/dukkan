package com.dukkan.store.user;

import com.dukkan.store.system.exception.ObjectNotFoundException;
import com.dukkan.store.system.exception.UserEmailExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;


    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        return users;
    }

    public User save(User user) {
        boolean doesEmailExists = repository.existsByEmail(user.getEmail());
        if (doesEmailExists){
            throw new UserEmailExistsException(user.getEmail());
        }
        User savedUser = repository.save(user);
        return savedUser;
    }

    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("user","id",id.toString()));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(()->new ObjectNotFoundException("user","email",email));
    }

    public User update(Integer id, User user) {
        User targetUser = repository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("user","id",id.toString()));

        if(user.getName()!=null) targetUser.setName(user.getName());
        if(user.getEmail()!=null) targetUser.setEmail(user.getEmail());
        if(user.getAddress()!=null) targetUser.setAddress(user.getAddress());
        if(user.getPhoneNumber()!=null) targetUser.setPhoneNumber(user.getPhoneNumber());
        if(user.getRoles()!=null) targetUser.setRoles(user.getRoles());
        targetUser.setActivate(user.isActivate());

        User savedUser = repository.save(targetUser);
        return savedUser;
    }

    public void delete(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("user","id",id.toString()));
        repository.delete(user);
    }

    public User updateActivation(Integer userId, boolean isActivate) {
        User targetUser = repository.findById(userId)
                .orElseThrow(()->new ObjectNotFoundException("user","id",userId.toString()));

        if (targetUser.isActivate() != isActivate)
            targetUser.setActivate(isActivate);

        return repository.save(targetUser);
    }

}
