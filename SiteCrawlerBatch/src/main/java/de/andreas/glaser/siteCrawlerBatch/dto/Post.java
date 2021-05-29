package de.andreas.glaser.siteCrawlerBatch.dto;

import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private String modified;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private Title title;

    @Getter
    @Setter
    private Content content;

    public Blog mapToBlog() {
        var blog = new Blog();
        blog.setTitle(title.getRendered());
        blog.setContent(content.getRendered());
        blog.setCreationDate(parseDate(date));
        blog.setLastUpdated(parseDate(modified));

        return blog;
    }



    private Date parseDate(String date) {
        try {
            return format.parse(date);
        } catch (java.text.ParseException e) {
            return null;
        }
    }
}
