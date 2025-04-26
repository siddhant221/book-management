package com.bookstore.bookmanagement.exception;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String resource,Long id)
    {
        super(resource+" not found with id " + id);
    }
}
