package com.alan.feedapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	public @ResponseBody Map<String, Object> feeds() {
		log.info("feed service result: ----> ");
		Map response = new HashMap<String, Object>();
		response.put("feeds", feedService.getAllFeeds());
		return response;
	}
}
