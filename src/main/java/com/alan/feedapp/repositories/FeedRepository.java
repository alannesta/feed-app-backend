package com.alan.feedapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.alan.feedapp.models.Feed;

public interface FeedRepository extends CrudRepository<Feed, Long> {

	List<Feed> findByFeedUrl(String feedUrl);

}

