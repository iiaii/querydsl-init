package me.iiaii.querydslinit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.iiaii.querydslinit.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.iiaii.querydslinit.domain.QBook.book;

@Repository
@RequiredArgsConstructor
public class BookQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Book> findByTitle(String title) {
        return queryFactory.selectFrom(book)
                .where(book.title.eq(title))
                .fetch();
    }
}
