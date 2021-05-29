package de.andreas.glaser.siteCrawlerBatch.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {
    final String TITLE = "Title";
    final String CONTENT = "Content";
    final String DATE_STRING = "2020-08-12T11:34:21";
    final long DATE_LONG = 1597224861000L;

    @Test
    public void mapToBlog()
    {
        var post = new Post();
        post.setTitle(new Title(TITLE));
        post.setContent(new Content(CONTENT, false));
        post.setDate(DATE_STRING);
        post.setModified(DATE_STRING);

        var blog = post.mapToBlog();
        assertEquals(TITLE, blog.getTitle());
        assertEquals(CONTENT, blog.getContent());
        assertEquals(DATE_LONG, blog.getCreationDate().getTime());
        assertEquals(DATE_LONG, blog.getLastUpdated().getTime());

    }
}
