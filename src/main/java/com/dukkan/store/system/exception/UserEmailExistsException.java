package com.dukkan.store.system.exception;

public class UserEmailExistsException extends RuntimeException {
    public UserEmailExistsException(String email) {
        super("can't save user with email : "+email+"\nEmail Already Exists!");
    }
}
