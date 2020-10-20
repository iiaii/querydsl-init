package me.iiaii.querydslinit.repository;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import me.iiaii.querydslinit.domain.Book;
import org.junit.After;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositorySupportTest {

    // spring data jpa Repository
    @Autowired
    private BookRepository bookRepository;

    // 가장 기본적인 querydsl Repository (잘 안쓰임)
    @Autowired
    private BookRepositorySupport bookRepositorySupport;

    // 보편적으로 쓰이는 querydsl Repository
    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;

    // 상속, 구현이 없는 Repository
    @Autowired
    private BookQueryRepository bookQueryRepository;

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAllInBatch();
    }

    @Test
    public void SpringDataJpa_기본_기능_확인() throws Exception {
        // given
        String title = "월가의 영웅";
        String author = "Peter Lynch";
        bookRepository.save(new Book(title, author));

        // when
        List<Book> result = bookRepository.findByTitle(title);

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAuthor(), is(author));
    }

    @Test
    public void 기본적인_Querydsl_기능_확인() throws Exception {
        // given
        String title = "월가의 영웅";
        String author = "Peter Lynch";
        bookRepository.save(new Book(title, author));

        // when
        List<Book> result = bookRepositorySupport.findByTitle(title);

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAuthor(), is(author));
    }

    @Test
    public void 보편적인_Querydsl_조회_기능_확인() throws Exception {
        // given
        String title = "월가의 영웅";
        String author = "Peter Lynch";
        bookRepository.save(new Book(title, author));

        // when
        List<Book> result = bookRepositoryImpl.findByTitle(title);

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAuthor(), is(author));
    }

    @Test
    @Transactional
    public void 보편적인_Querydsl_삭제_기능_확인() throws Exception {
        // given
        String title1 = "월가의 영웅";
        String author1 = "Peter Lynch";
        String title2 = "이기는 투자";
        String author2 = "Peter Lynch";
        bookRepository.save(new Book(title1, author1));
        bookRepository.save(new Book(title2, author2));

        // when
        bookRepositoryImpl.deleteAllByTitles(Arrays.asList("월가의 영웅"));
        List<Book> result = bookRepositoryImpl.findAll();

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getTitle(), is(title2));
        assertThat(result.get(0).getAuthor(), is(author2));
    }

    @Test
    @Transactional
    public void 상속_구현_없는_Querydsl_조회_기능_확인() throws Exception {
        // given
        String title = "월가의 영웅";
        String author = "Peter Lynch";
        bookRepository.save(new Book(title, author));

        // when
        List<Book> result = bookQueryRepository.findByTitle(title);

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAuthor(), is(author));
    }

    @Test
    @Transactional
    public void 상속_구현_없는_Querydsl_삭제_기능_확인() throws Exception {
        // given
        String title1 = "월가의 영웅";
        String author1 = "Peter Lynch";
        String title2 = "이기는 투자";
        String author2 = "Peter Lynch";
        bookRepository.save(new Book(title1, author1));
        bookRepository.save(new Book(title2, author2));

        // when
        bookQueryRepository.deleteAllByTitles(Arrays.asList("월가의 영웅"));
        List<Book> result = bookQueryRepository.findAll();

        // then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getTitle(), is(title2));
        assertThat(result.get(0).getAuthor(), is(author2));
    }
}