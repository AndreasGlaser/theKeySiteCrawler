package de.andreas.glaser.siteCrawlerBatch;

import de.andreas.glaser.siteCrawlerBatch.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final BlogRepository blogRepository;

    @Autowired
    public JobCompletionNotificationListener(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("BlogWordCountBatch finished");

            blogRepository.findAll().forEach(blog -> log.info("Found <" + blog.getTitle() + "> in the database."));
        }
    }
}
