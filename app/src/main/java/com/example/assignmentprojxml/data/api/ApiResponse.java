package com.example.assignmentprojxml.data.api;

/**
 * Generic API response wrapper for better error handling
 */
public class ApiResponse<T> {
    private final Status status;
    private final T data;
    private final String message;
    private final Throwable error;

    private ApiResponse(Status status, T data, String message, Throwable error) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Status.SUCCESS, data, null, null);
    }

    public static <T> ApiResponse<T> error(String message, Throwable error) {
        return new ApiResponse<>(Status.ERROR, null, message, error);
    }

    public static <T> ApiResponse<T> loading() {
        return new ApiResponse<>(Status.LOADING, null, null, null);
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return error;
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}