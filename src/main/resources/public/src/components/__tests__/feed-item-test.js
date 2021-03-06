jest.dontMock('../feed-item');
jest.dontMock('classnames');
//jest.dontMock('../../actions/feed-actions');

var React = require('react');
var ReactDOM = require('react-dom');
var TestUtils = require('react-addons-test-utils');

var FeedItem = require('../feed-item');
var FeedActions = require('../../actions/feed-actions');


describe('Feed Item Test', function() {

	it('should render correctly', function() {
		// Render a checkbox with label in the document
		var feedItem = TestUtils.renderIntoDocument(
			<FeedItem selected = {{_id: 1, name: 'kaka'}} feed = {{_id: 1, name: 'kaka'}} />
		);

		var feedItemNode = ReactDOM.findDOMNode(feedItem);
		//console.log(feedItemNode.outerHTML);

		expect(feedItemNode.className).toContain('selected');

		var feedItem2 = TestUtils.renderIntoDocument(
			<FeedItem selected = {{_id: 2, name: 'lala'}} feed = {{_id: 1, name: 'kaka'}} />
		);

		var feedItemNode2 = ReactDOM.findDOMNode(feedItem2);
		expect(feedItemNode2.className).not.toContain('selected');
	});

	it('should toggle the delete button on mouse events', ()=>{
		var mockFeed = {_id: 1, name: 'kaka'};
		var feedItem = TestUtils.renderIntoDocument(
			<FeedItem selected = {{_id: 1, name: 'kaka'}} feed = {mockFeed} />
		);
		var feedItemNode = ReactDOM.findDOMNode(feedItem);
		TestUtils.Simulate.mouseEnter(
			feedItemNode
		);
		expect(FeedActions.toggleFeedActions).toBeCalledWith(mockFeed);
		//console.log(feedItem.props);

	});

	it('should delete a feed', ()=>{
		var mockFeed = {_id: 1, name: 'kaka'};
		var feedItem = TestUtils.renderIntoDocument(
			<FeedItem selected = {{_id: 1, name: 'kaka'}} feed = {mockFeed} />
		);
		var feedItemNode = ReactDOM.findDOMNode(feedItem);
		var deleteButton = feedItemNode.getElementsByTagName('button')[0];


		TestUtils.Simulate.click(
			deleteButton
		);
		expect(FeedActions.deleteFeed).toBeCalledWith(mockFeed, true);
		//console.log(feedItem.props);

	})

});
