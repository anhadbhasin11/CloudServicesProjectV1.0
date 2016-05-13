var express = require('express');
var path = require('path');
var http = require('http');

var routes = require('./routes/index');
var aws = require('./aws');

var app = express();

app.use(express.static(path.join(__dirname, '../')));

routes.setup(app, aws);

/**
 * Get port from environment and store in Express.
 */

var port = '3001';
app.set('port', port);

/**
 * Create HTTP server.
 */

var server = http.createServer(app);

/**
 * Listen on provided port, on all network interfaces.
 */

server.listen(port);
console.log('Server started on: ', port);