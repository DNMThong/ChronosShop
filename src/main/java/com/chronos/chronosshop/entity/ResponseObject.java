package com.chronos.chronosshop.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Generated
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
