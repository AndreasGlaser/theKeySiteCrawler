package de.andreas.glaser.theKeySiteCrawler;

import de.andreas.glaser.theKeySiteCrawler.DO.Blog;
import de.andreas.glaser.theKeySiteCrawler.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Stream;

@SpringBootApplication
public class BlogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BlogRepository blogRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                var blog = new Blog();
                blog.setTitle(name);
                blog.setCreationDate(Date.from(Instant.now()));
                blog.setContent("content");
                var map = new HashMap<String, Integer>();
                map.put("test", 5);
                blog.setCountOfWords(map);
                blogRepository.save(blog);
            });
            blogRepository.findAll().forEach(blog -> System.out.println(blog.getTitle()));
        };
    }

}
