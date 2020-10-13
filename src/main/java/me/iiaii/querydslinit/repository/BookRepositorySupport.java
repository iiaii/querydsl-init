package me.iiaii.querydslinit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.iiaii.querydslinit.domain.Book;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.iiaii.querydslinit.domain.QBook.book;


@Repository
public class BookRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public BookRepositorySupport(JPAQueryFactory queryFactory) {
        super(Book.class);
        this.queryFactory = queryFactory;
    }

    public List<Book> findByTitle(String title) {
        return queryFactory
                .selectFrom(book)
                .where(book.title.eq(title))
                .fetch();
    }

}
