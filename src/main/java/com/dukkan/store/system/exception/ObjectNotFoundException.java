package com.dukkan.store.system.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, String fieldName, String value) {
        super("Could not find " + objectName + " with " + fieldName +" : "+value+ " :(");
    }
}
