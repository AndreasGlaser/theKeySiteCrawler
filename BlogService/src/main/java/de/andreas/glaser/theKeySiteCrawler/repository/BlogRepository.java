package de.andreas.glaser.theKeySiteCrawler.repository;

import de.andreas.glaser.theKeySiteCrawler.DO.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
