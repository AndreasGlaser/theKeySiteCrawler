package de.andreas.glaser.theKeySiteCrawler.DO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blog_word_count")
public class BlogWordCount {

    @Setter
    @Getter
    @Id
    private Long id;

    @Setter
    @Getter
    @Column(name = "blog_id")
    private Long blogId;

    @Setter
    @Getter
    private String word;

    @Setter
    @Getter
    private Integer count;

}
