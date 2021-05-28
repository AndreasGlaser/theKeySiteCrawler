package de.andreas.glaser.siteCrawlerBatch.repository;


import de.andreas.glaser.siteCrawlerBatch.DO.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
