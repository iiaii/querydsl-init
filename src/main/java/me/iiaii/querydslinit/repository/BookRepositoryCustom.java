package me.iiaii.querydslinit.repository;

import me.iiaii.querydslinit.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findAll();

    List<Book> findByTitle(String title);

    void deleteAllByTitles(List<String> titles);
}
