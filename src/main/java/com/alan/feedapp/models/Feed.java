package com.alan.feedapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Feed")
@XmlRootElement
public class Feed {

	@Id
	@GeneratedValue
	private long id;
	private String feedName;
	private String feedUrl;

	protected Feed() {}

	public Feed(String feedName, String feedUrl) {
		this.feedName = feedName;
		this.feedUrl = feedUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
}
