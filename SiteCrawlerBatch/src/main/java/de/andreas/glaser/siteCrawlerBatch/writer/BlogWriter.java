package de.andreas.glaser.siteCrawlerBatch.writer;

import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import de.andreas.glaser.siteCrawlerBatch.repository.BlogRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class BlogWriter implements ItemWriter<Blog> {
    BlogRepository blogRepository;
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/blogs";

    public BlogWriter(BlogRepository blogRepository, RestTemplate restTemplate)
    {
        this.blogRepository = blogRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void write(List<? extends Blog> list){
        blogRepository.saveAll(list);
        sendBlogs(list);
    }

    private void sendBlogs(List<? extends Blog> list) {
        restTemplate.put(apiUrl, list);
    }
}
