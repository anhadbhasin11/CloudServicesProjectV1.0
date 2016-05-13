var passport = require('passport');
var Auth0Strategy = require('passport-auth0');

var strategy = new Auth0Strategy({
    domain:       'cloudmafia.auth0.com',
    clientID:     'Oy9FHpLdvSt7LaGn1D7CWRM8Ld2ABjnP',
    clientSecret: 'jzkKC2pHIcuR6NY227lnLLSq2K2574q3P39hNGjKoP-5RlX6323yifdK_nCMQTy1',
    callbackURL:  'http://ec2-54-152-31-219.compute-1.amazonaws.com:3001/callback'
  }, function(accessToken, refreshToken, extraParams, profile, done) {
    // accessToken is the token to call Auth0 API (not needed in the most cases)
    // extraParams.id_token has the JSON Web Token
    // profile has all the information from the user
    return done(null, profile);
  });

passport.use(strategy);

// This is not a best practice, but we want to keep things simple for now
passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});

module.exports = strategy;