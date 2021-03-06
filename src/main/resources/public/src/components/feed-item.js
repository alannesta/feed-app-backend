var React = require('react');
var FeedActions = require('../actions/feed-actions');
var classNames = require('classnames');

var FeedItem = React.createClass({

	selectFeed: function () {
		FeedActions.selectFeed(this.props.feed);
	},

	deleteFeed: function () {
		var reselectFlag = this.props.selected.id === this.props.feed.id;
		FeedActions.deleteFeed(this.props.feed, reselectFlag);
	},

	showActions: function () {
		FeedActions.showFeedActions(this.props.feed);
	},

	hideActions: function () {
		FeedActions.hideFeedActions(this.props.feed);
	},



	render: function () {
		var selectedClass = classNames({
			'selected': this.props.selected.id === this.props.feed.id,
			'feed-nav-item': true
		});

		var feedAction = classNames({
			'show-actions': this.props.feed.showActions
		});

		return (
			<li className={selectedClass} onMouseEnter={this.showActions}
				onMouseLeave={this.hideActions}>
				<section id="content"  onClick={this.selectFeed}>{this.props.feed.feedName}</section>
				<section id="actions" className={feedAction}>
					<button onClick={this.deleteFeed}>Delete</button>
				</section>
			</li>
			//<li onClick={FeedActions.selectFeed.bind(this, this.props.feed)}>{this.props.feed.name}</li>	// more straight forward
		)
	}
});

module.exports = FeedItem;
