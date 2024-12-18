package org.shad.thursday.warmup.entity;

public interface Response {
    String serialize();

    String getStatus();

    String getBody();
}
