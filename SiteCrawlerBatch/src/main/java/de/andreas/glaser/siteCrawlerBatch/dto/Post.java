package de.andreas.glaser.siteCrawlerBatch.dto;

import lombok.Getter;
import lombok.Setter;

public class Post {

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
}
