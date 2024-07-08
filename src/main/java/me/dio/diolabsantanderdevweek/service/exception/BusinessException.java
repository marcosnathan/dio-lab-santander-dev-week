package me.dio.diolabsantanderdevweek.service.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
