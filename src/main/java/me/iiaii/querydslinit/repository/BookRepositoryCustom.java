package me.iiaii.querydslinit.repository;

import me.iiaii.querydslinit.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findByTitle(String title);
}
