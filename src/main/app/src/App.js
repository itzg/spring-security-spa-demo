import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import {withStyles} from '@material-ui/core/styles';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import LoginRegister from 'react-mui-login-register';
import LoginStatus from "./LoginStatus";
import Logout from "./Logout";

const style = theme => ({
  root: {
    margin: 2 * theme.spacing.unit
  }
});

class App extends Component {
  state = {
    loggedIn: false,
    loading: true,
    username: null,
    loginError: null,
    registerError: null
  };

  render() {
    const {
      loggedIn,
      loading,
      loginError,
      registerError
    } = this.state;
    const {
      classes
    } = this.props;

    if (loading) {
      return <div className={classes.root}>Loading...</div>
    }
    else if (!loggedIn) {
      return <div className={classes.root}>
        <LoginRegister
            providers={["github"]}
            onLoginWithProvider={this.handleLoginWithProvider}
            onRegisterWithProvider={this.handleLoginWithProvider}
            onLogin={this.handleLoginLocal}
            onRegister={this.handleRegisterLocal}
            loginFailed={loginError}
            registerFailed={registerError}
        />
      </div>
    }
    else {
      return <Card className={classes.root}>
        <CardContent>
          <LoginStatus username={this.state.username}/>
        </CardContent>
        <CardActions>
          <Logout onLogout={this.handleLogout}/>
        </CardActions>
      </Card>
    }
  }

  componentDidMount() {
    this.fetchProfile();
  }

  handleLoginWithProvider = provider => {
    window.location.href = `/oauth2/authorization/${provider}`;
  };

  handleLoginLocal = (details) => {
    fetch('/login/local', {
      method: 'POST',
      body: JSON.stringify(details),
      credentials: "same-origin",
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      if (response.ok) {
        this.fetchProfile();
      }
      else {
        response.text().then(content => {
          this.setState({
            loginError: content
          });
        })
      }
    })
  };

  handleRegisterLocal = (details) => {
    fetch('/register/local', {
      method: 'POST',
      body: JSON.stringify(details),
      credentials: "same-origin",
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      if (response.ok) {
        this.fetchProfile();
      }
      else {
        response.text().then(content => {
          this.setState({
            registerError: content
          });
        })
      }
    })
  };

  handleLogout = () => {
    fetch('/logout', {
      credentials: "same-origin",
      method: "POST"
    })
    .then(() => {
      this.setState({
        loggedIn: false,
        username: null
      })
    })
  };

  fetchProfile() {
    fetch("/api/profile", {
      credentials: "same-origin"
    })
    .then(response => {
      return response.json();
    })
    .then(content => {
      this.setState({
        loggedIn: content.loggedIn,
        loading: false,
        username: content.username,
        loginError: null,
        registerError: null
      })
    })
  }

}

export default withStyles(style)(App);
