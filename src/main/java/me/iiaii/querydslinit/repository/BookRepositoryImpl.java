package me.iiaii.querydslinit.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.iiaii.querydslinit.domain.Book;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import static me.iiaii.querydslinit.domain.QBook.book;

/**
 * 보편적인 Querydsl Repository
 */
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 모든 책 조회
     * @return
     */
    @Override
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
    @Override
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
    @Override
    public void deleteAllByTitles(List<String> titles) {
        queryFactory
            .delete(book)
            .where(book.title.in(titles))
            .execute();
    }
}
