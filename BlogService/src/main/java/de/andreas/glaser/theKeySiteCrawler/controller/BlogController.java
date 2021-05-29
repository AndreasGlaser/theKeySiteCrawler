package de.andreas.glaser.theKeySiteCrawler.controller;

import de.andreas.glaser.theKeySiteCrawler.DO.Blog;
import de.andreas.glaser.theKeySiteCrawler.repository.BlogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:9090"})
public class BlogController {

    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogs() {
        var foundBlogs = new ArrayList<Blog>();
        blogRepository.findAll().iterator().forEachRemaining(foundBlogs::add);
        return new ResponseEntity<>(foundBlogs, HttpStatus.OK);
    }

    @PutMapping("/blogs")
    public ResponseEntity<List<Blog>> putBlogs(@RequestBody Blog[] blogs) {
        blogRepository.saveAll(Arrays.asList(blogs));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/blogs/{blogId}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long blogId) {
        return blogRepository.findById(blogId).map(blog -> new ResponseEntity<>(blog, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
