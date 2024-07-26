package io.zact.zone.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponseDTO {
    private int statusCode;
    private JsonNode body;

    public ResponseDTO(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public JsonNode getBody() {
        return body;
    }

    public void setBody(JsonNode body) {
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
