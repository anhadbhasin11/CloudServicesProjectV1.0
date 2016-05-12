/**
 * Created by vidyakhadsare on 5/8/16.
 */

var React = require('react');

var Login = React.createClass({
  getInitialState: function () {
    return {
      msg: ''
    }
  },

  render: function() {
    return (
        <div id="login" className="container">
          <div className="row-fluid">
            <div className="span12">
              <div className="login well well-small">
                <div className="login-msg">{this.state.msg}</div>
                <div className="center">
                  <img src="http://placehold.it/250x100&text=Ground to Cloud" alt=“Ground to Cloud“ />
                </div>
                <div className="login-form" id="UserLoginForm">
                  <div className="control-group">
                    <div className="input-prepend">
                      <span className="add-on"></span>
                      <input name="data[User][username]" ref="username" required="required" placeholder="Username" maxlength="255" type="text" id="UserUsername" />
                    </div>
                  </div>
                  <div className="control-group">
                    <div className="input-prepend">
                      <span className="add-on"></span>
                      <input name="data[User][password]" ref="password" required="required" placeholder="Password" type="password" id="UserPassword" />
                    </div>
                  </div>
                  <div className="control-group" onClick={this.login}>
                    <input className="btn btn-primary btn-large btn-block" type="submit" value="Sign in" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
    );
  },

  login: function () {
    var username = this.refs.username.value;
    var password = this.refs.password.value;

    if (!(username && password)) {
      this.setState({
        msg: 'Please enter username and password.'
      });
      return;
    }

    //call ajax
    this.onLogin();
  },

  onLogin: function () {
    /*
    if error


    this.setState({
      msg: 'Could not login.'
    });
    */
    this.props.onLoginSuccess();
  }
});

module.exports = Login;