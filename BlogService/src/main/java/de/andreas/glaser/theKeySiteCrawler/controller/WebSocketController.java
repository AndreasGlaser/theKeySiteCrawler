package de.andreas.glaser.theKeySiteCrawler.controller;

import de.andreas.glaser.theKeySiteCrawler.DO.Blog;
import de.andreas.glaser.theKeySiteCrawler.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {

    private final BlogRepository blogRepository;

    @Autowired
    WebSocketController(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @MessageMapping("/blogs")
    @SendTo("/blogs")
    public List<Blog> blogs() {
        var foundBlogs = new ArrayList<Blog>();
        blogRepository.findAll().iterator().forEachRemaining(foundBlogs::add);
        return foundBlogs;
    }

}
