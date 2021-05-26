package de.andreas.glaser.theKeySiteCrawler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class Blog {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private Map<String, Integer> wordCount;

}
