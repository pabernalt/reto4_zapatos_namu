package com.example.mongo_back.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Counter {
    @Id
    private String id;

    private Long seq;

    public Counter(String id, Long seq) {
        this.id = id;
        this.seq = seq;
    }
}
