package de.andreas.glaser.theKeySiteCrawler.DO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@NoArgsConstructor
@Table(name = "blog")
public class Blog {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 10000)
    private String content;

    @Getter
    @Setter
    @ElementCollection
    private Map<String, Integer> countOfWords;
}
