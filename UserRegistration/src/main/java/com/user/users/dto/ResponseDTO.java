package com.user.users.dto;

import lombok.Data;

public @Data class ResponseDTO {
    private String message;
    private Object object;
    private String token;

    public ResponseDTO(String message, Object object, String token){
        this.message = message;
        this.object = object;
        this.token = token;
    }
}
