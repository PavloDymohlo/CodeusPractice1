package org.shad.thursday.warmup.entity.responseimpl;

import org.shad.thursday.warmup.entity.Response;

public class UnsuccessfulResponse implements Response {
    private final String status;
    private final String bodyResponse;

    public UnsuccessfulResponse(String status, String bodyResponse) {
        this.status = "HTTP/1.0 " + status;
        this.bodyResponse = "{\"error\":\"" + bodyResponse + "\"}";
    }

    public String serialize() {
        return this.status + "\r\n" + "Content-length: " + this.bodyResponse.length() + "\r\n" +
                "Content-type: application/json"+ "\r\n" + "\r\n" + this.bodyResponse;
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return bodyResponse;
    }
}
