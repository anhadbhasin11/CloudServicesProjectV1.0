var express = require('express');
var path = require('path');
var http = require('http');

var routes = require('./routes/index');
var aws = require('./aws');

var app = express();

var passport = require('passport');

// This is the file we created in step 2.
// This will configure Passport to use Auth0
var strategy = require('setup-passport.js');

// Session and cookies middlewares to keep user logged in
var cookieParser = require('cookie-parser');
var session = require('express-session');

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
app.use(cookieParser());
app.use(session({ secret: 'YOUR_SECRET_HERE', resave: false,  saveUninitialized: false }));
app.use(passport.initialize());
app.use(passport.session());

app.get('/callback',
  passport.authenticate('auth0', { failureRedirect: '/url-if-something-fails' }),
  function(req, res) {
    if (!req.user) {
      throw new Error('user null');
    }
    res.redirect("/user");
  });

app.get('/user', function (req, res) {
  res.render('user', {
    user: req.user
  });
});
server.listen(port);
console.log('Server started on: ', port);