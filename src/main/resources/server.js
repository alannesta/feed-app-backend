var express = require('express');
var path = require('path');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var api = require('./server/api');

var app = express();

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static('public/dist'));


//using regex to support backbone pushState:true
app.get(/^\/app(\/\w*)*$/, function (req, res) {
	console.log(path.join(__dirname, 'public/index.html'));
	res.sendFile(path.join(__dirname, 'public/index.html'));
});

app.use('/api', api);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
	var err = new Error('Not Found');
	err.status = 404;
	next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
	app.use(function (err, req, res, next) {
		res.status(err.status || 500);
		res.send({
			message: err.message,
			error: err
		});
		next();
	});
}
//
//// production error handler
//// no stacktraces leaked to user
//app.use(function(err, req, res, next) {
//    res.status(err.status || 500);
//    res.render('error', {
//        message: err.message,
//        error: {}
//    });
//});

app.set('port', process.env.PORT || 3000);

var server = app.listen(app.get('port'), function () {
	console.log('Express server listening on port ' + server.address().port);
});
