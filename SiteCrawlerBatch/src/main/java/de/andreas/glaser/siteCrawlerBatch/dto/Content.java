package de.andreas.glaser.siteCrawlerBatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Content {

    @Getter
    @Setter
    private String rendered;

    @Setter
    @Getter
    @JsonProperty(value = "protected")
    private Boolean protectedContent;
}
