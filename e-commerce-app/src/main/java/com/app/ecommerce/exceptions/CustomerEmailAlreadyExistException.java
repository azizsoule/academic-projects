package com.app.ecommerce.exceptions;

public class CustomerEmailAlreadyExistException extends Exception{

    public CustomerEmailAlreadyExistException(String message) {
        super(message);
    }

}
