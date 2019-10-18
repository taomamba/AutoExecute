package com.inspur.auto.repositories;

import com.inspur.auto.entity.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;


public interface BookRepository extends ElasticsearchCrudRepository<Book, String> {
    Book findByName(String name);

    List<Book> findByAuthor(String author);

    Book findBookById(String id);
}
