package com.mirror;

public record ApiErrorResponse(ApiError error, String data, String message) {

}
