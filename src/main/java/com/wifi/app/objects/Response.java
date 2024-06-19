package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {

    private String message;
    private String debugMessage;

    public Response() {
    }

    public Response(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
