package com.jesus.courses.springboot.apirest.app.models.dao;

import com.jesus.courses.springboot.apirest.app.models.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestResponse {
    private HttpStatus status;
    private Map<String, Object> payload;
    private String message;
    private Boolean ok;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        setOk(status.value() >= 200 && status.value() < 400);
        this.status = status;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(String key, Object value) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(key, value);
        this.payload = payload;
    }

    private void setInternalPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getOk() {
        return ok;
    }

    private void setOk(Boolean ok) {
        this.ok = ok;
    }

    public ResponseEntity<RequestResponse> respond() {
        RequestResponse body = new RequestResponse();

        body.setStatus(Objects.requireNonNullElse(status, HttpStatus.OK));

        body.setMessage(message);
        body.setInternalPayload(payload);
        return new ResponseEntity<RequestResponse>(body, status);
    }
}
