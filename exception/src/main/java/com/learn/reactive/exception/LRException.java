package com.learn.reactive.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class LRException extends RuntimeException{
    private Map<String, List<String>> errors;

    public LRException(String message) {
        super(message);

        this.errors = new HashMap<String, List<String>>() {{
            put("errorMessage", Collections.singletonList(message));
        }};
    }

    public LRException(List<String> messages) {
        this.errors = new HashMap<String, List<String>>() {{
            put("errorMessage", messages);
        }};
    }

    public LRException(Throwable throwable) {
        super(throwable);
    }
}
