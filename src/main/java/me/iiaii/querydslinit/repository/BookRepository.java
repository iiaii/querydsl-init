package me.iiaii.querydslinit.repository;


import me.iiaii.querydslinit.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
