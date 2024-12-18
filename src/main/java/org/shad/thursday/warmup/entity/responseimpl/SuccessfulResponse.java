package org.shad.thursday.warmup.entity.responseimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shad.thursday.warmup.entity.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SuccessfulResponse implements Response {
    private String status;
    private String bodyResponse;

    public SuccessfulResponse (String status, String bodyResponse) {
        ObjectMapper mapper = new ObjectMapper();
        this.status = "HTTP/1.0 " + status;
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", bodyResponse);

        try {
            this.bodyResponse = mapper.writeValueAsString(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize response body", e);
        }
    }

    public String serialize() {
        return this.status + "\r\n" + "Content-length: " + this.bodyResponse.length() + "\r\n" +
                "Content-type: application/json" + "\r\n" + "\r\n" + this.bodyResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return bodyResponse;
    }

    public void setBodyResponse(String bodyResponse) {
        this.bodyResponse = bodyResponse;
    }
}
