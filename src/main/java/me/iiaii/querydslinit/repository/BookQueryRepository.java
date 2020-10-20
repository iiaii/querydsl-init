package me.iiaii.querydslinit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.iiaii.querydslinit.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.iiaii.querydslinit.domain.QBook.book;

/**
 * 상속, 구현 없는 Repository
 */
@Repository
@RequiredArgsConstructor
public class BookQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 모든 책 조회
     * @return
     */
    public List<Book> findAll() {
        return queryFactory
            .selectFrom(book)
            .fetch();
    }

    /**
     * title 로 조회
     * @param title
     * @return
     */
    public List<Book> findByTitle(String title) {
        return queryFactory
            .selectFrom(book)
            .where(book.title.eq(title))
            .fetch();
    }

    /**
     * title 로 삭제
     * @param titles
     */
    public void deleteAllByTitles(List<String> titles) {
        queryFactory
            .delete(book)
            .where(book.title.in(titles))
            .execute();
    }
}
