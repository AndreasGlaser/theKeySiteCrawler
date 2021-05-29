package de.andreas.glaser.siteCrawlerBatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Content {

    @Getter
    @Setter
    private String rendered;

    @Setter
    @Getter
    @JsonProperty(value = "protected")
    private Boolean protectedContent;
}
