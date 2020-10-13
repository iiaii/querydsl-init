package me.iiaii.querydslinit.repository;

import me.iiaii.querydslinit.domain.Book;
import org.junit.After;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositorySupportTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositorySupport bookRepositorySupport;

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_기본_기능_확인() throws Exception {
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
    public void querydsl_custom_설정_기능_확인() throws Exception {
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
    public void querydsl_상속받지_않은_기본기능확인() throws Exception {
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
}