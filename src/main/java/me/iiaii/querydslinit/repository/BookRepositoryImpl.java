package me.iiaii.querydslinit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.iiaii.querydslinit.domain.Book;

import java.util.List;

import static me.iiaii.querydslinit.domain.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Book> findByTitle(String title) {
        return queryFactory.selectFrom(book)
                .where(book.title.eq(title))
                .fetch();
    }
}
