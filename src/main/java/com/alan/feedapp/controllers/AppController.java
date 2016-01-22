package com.alan.feedapp.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alan.feedapp.models.Feed;
import com.alan.feedapp.services.FeedService;

@RestController
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	@Autowired
	FeedService feedService;

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public Map<String, String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Map response = new HashMap<String, String>();
		response.put("result", name);
		return response;
	}

	@RequestMapping(value = "/feeds", method = RequestMethod.GET)
	@ResponseBody
	public List<Feed> feeds() {
		log.info("feed service result: ----> ");
		return feedService.getAllFeeds();
	}

	//	public Map<String, Object> feeds() {
//		log.info("feed service result: ----> ");
//		Map response = new HashMap<String, Object>();
//		response.put("feeds", feedService.getAllFeeds());
//		return response;
//	}
	@RequestMapping(value = "/feed", method = RequestMethod.POST)
	public Map<String, Object> saveFeed(@RequestBody Map<String, Object> feedMap) {
		Feed feed = new Feed(feedMap.get("name").toString(),
				feedMap.get("feedUrl").toString());

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		try {
			feedService.saveFeed(feed);
			response.put("message", "Feed saved successfully");
		} catch (Exception e){
			response.put("message", "Feed already exists");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/feed/{feedId}")
	public Map<String, String> deleteFeed(@PathVariable("feedId") String feedId) {
		String result = feedService.deleteFeed(feedId);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", result);

		return response;
	}
}
