import React, {Component} from 'react';
import PropTypes from 'prop-types';

class LoginStatus extends Component {

  static propTypes = {
    username: PropTypes.string.isRequired
  };


  render() {
    return <div>Congratulations, you're logged in as {this.props.username}</div>
  }
}

export default LoginStatus;