package com.alan.feedapp.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.feedapp.models.Feed;
import com.alan.feedapp.repositories.FeedRepository;

@Service
public class FeedService {

	@Autowired
	FeedRepository feedRepository;

	public List<Feed> getAllFeeds() {
		List<Feed> allFeeds = (List<Feed>)feedRepository.findAll();
		return allFeeds;
	}

	public Feed saveFeed(Feed feed) throws Exception{

		List<Feed> exist = feedRepository.findByFeedUrl(feed.getFeedUrl());
		if (exist.size() > 0) {
			throw new Exception("Already exists");
		} else {
			return feedRepository.save(feed);
		}
	}

	public String deleteFeed(String feedId) {
		try {
			feedRepository.delete(Long.parseLong(feedId, 10));
			return "Delete Success";
		} catch (Exception e) {
			return "Fail to delete";
		}
	}
}
