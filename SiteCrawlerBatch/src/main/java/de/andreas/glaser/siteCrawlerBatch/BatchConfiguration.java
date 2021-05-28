package de.andreas.glaser.siteCrawlerBatch;

import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import de.andreas.glaser.siteCrawlerBatch.processor.BlogWordCountProcessor;
import de.andreas.glaser.siteCrawlerBatch.reader.BlogReader;
import de.andreas.glaser.siteCrawlerBatch.repository.BlogRepository;
import de.andreas.glaser.siteCrawlerBatch.writer.BlogWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    private final BlogRepository blogRepository;
    private final RestTemplate restTemplate;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, BlogRepository blogRepository, RestTemplate restTemplate) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.blogRepository = blogRepository;
        this.restTemplate = restTemplate;
    }

    @Bean
    public ItemReader<Blog> reader() {
        return new BlogReader(restTemplate);
    }

    @Bean
    public BlogWordCountProcessor processor() {
        return new BlogWordCountProcessor();
    }

    @Bean
    public ItemWriter<Blog> writer(DataSource dataSource) {
        return new BlogWriter(blogRepository, restTemplate);
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(ItemWriter<Blog> writer) {
        return stepBuilderFactory.get("step1")
                .<Blog, Blog> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}