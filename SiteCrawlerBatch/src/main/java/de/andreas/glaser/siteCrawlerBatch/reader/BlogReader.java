package de.andreas.glaser.siteCrawlerBatch.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import de.andreas.glaser.siteCrawlerBatch.dto.Post;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class BlogReader implements ItemReader<Blog> {

    String apiUrl = "https://www.internate.org/wp-json/wp/v2/posts?_fields=modified,link,date,title,content";
    List<Blog> blogs;
    Integer nextBlogIndex = 0;
    private final RestTemplate restTemplate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public BlogReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Blog read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (blogs == null)
        {
            blogs = mapToBlogs(getPostsFromApi());
        }
        Blog nextBlog = null;

        if (nextBlogIndex < blogs.size()) {
            nextBlog = blogs.get(nextBlogIndex);
            nextBlogIndex++;
        }else {
            nextBlogIndex = 0;
            blogs = null;
        }
        return nextBlog;
    }

    private List<Blog> mapToBlogs(List<Post> postsFromApi) {
        return postsFromApi.stream().map(this::mapToBlog).collect(Collectors.toList());
    }

    private Blog mapToBlog(Post post) {
        var blog = new Blog();
        blog.setTitle(post.getTitle().getRendered());
        blog.setContent(post.getContent().getRendered());
        blog.setCreationDate(parseDate(post.getDate()));
        blog.setLastUpdated(parseDate(post.getModified()));

        return blog;
    }

    private Date parseDate(String date) {
        try {
            return format.parse(date);
        } catch (java.text.ParseException e) {
            return null;
        }
    }

    private List<Post> getPostsFromApi() throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl , String.class);
        ObjectMapper mapper = new ObjectMapper();
        var body = response.getBody();
        assert body != null;
        var bodyParts= body.split("</script>");
        assert bodyParts.length >= 1;
        var json = bodyParts[bodyParts.length - 1];

        Post[] posts = mapper.readValue(json, Post[].class);
        Arrays.stream(posts).forEach(post -> System.out.println(post.getTitle().getRendered()));

        return Arrays.asList(posts.clone());
    }

}
