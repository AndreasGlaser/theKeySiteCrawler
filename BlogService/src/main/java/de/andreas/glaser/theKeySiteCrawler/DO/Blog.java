package de.andreas.glaser.theKeySiteCrawler.DO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class Blog {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Date creationDate;

    @Getter
    @Setter
    private Date lastUpdated;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "blog_word_count",
            joinColumns = {@JoinColumn(name = "blog_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "word")
    @Column(name = "count")
    private Map<String, Integer> wordCount;
}
