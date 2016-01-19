package com.alan.feedapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Feed")
public class Feed {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String feedName;
	private String feedUrl;

	protected Feed() {}

	public Feed(String feedName, String feedUrl) {
		this.feedName = feedName;
		this.feedUrl = feedUrl;
	}

	@Override
	public String toString() {
		return String.format(
				"Feed[id=%d, firstName='%s', lastName='%s']",
				id, feedName, feedUrl);
	}

}
