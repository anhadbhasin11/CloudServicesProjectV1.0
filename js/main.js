/**
 * Created by vidyakhadsare on 5/8/16.
 */

var React = require('react');
var ReactDOM = require('react-dom');

var Login = require('./login/login');
var MainView = require('./mainview');

var onLoginSuccess = function () {
  render('main');
};

function render (state) {
  switch (state) {
    case 'login':
      ReactDOM.render(
          <Login onLoginSuccess={onLoginSuccess}/>,
          document.getElementById('app-container')
      );
      break;
    case 'main':
      ReactDOM.render(
        <MainView />,
        document.getElementById('app-container')
      );
      break;
  }
}

(function start () {
  render('login');
})();
