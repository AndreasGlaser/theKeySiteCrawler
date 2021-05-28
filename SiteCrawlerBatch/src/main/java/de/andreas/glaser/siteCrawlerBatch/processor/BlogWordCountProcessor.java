package de.andreas.glaser.siteCrawlerBatch.processor;

import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BlogWordCountProcessor implements ItemProcessor<Blog, Blog> {
    private static final Logger log = LoggerFactory.getLogger(BlogWordCountProcessor.class);

    @Override
    public Blog process(final Blog blog) {
        blog.setCountOfWords(countWords(blog.getContent()));
        log.info("Counted Words for the Blog with title:{}", blog.getTitle());
        return blog;
    }

    private Map<String, Integer> countWords(String content) {
        var words = parseWords(content);
        var distinctWords = words.stream().distinct().collect(Collectors.toList());
        final var wordCounts = new HashMap<String, Integer>();
        distinctWords.forEach(word -> wordCounts.put(word, countOccurrences(word, words)));
        return wordCounts;
    }

    private List<String> parseWords(String content) {
        var cleanedText = cleanText(content);
        return Arrays.asList(cleanedText.split(" "));
    }

    private String cleanText(String content) {
        return content;//TODO Cleaning
    }

    private Integer countOccurrences(String word, List<String> words) {
        var foundWords = words.stream().filter(word::equals);
        return (int) foundWords.count();
    }
}
