package com.inspur.auto.entity.po;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
@Data
@Document(indexName = "book", type = "book", shards = 1, replicas = 0, refreshInterval = "-1")
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
}
