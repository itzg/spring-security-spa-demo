import React, {Component} from 'react';
import Button from '@material-ui/core/Button';
import PropTypes from 'prop-types';

class Logout extends Component {
  static propTypes = {
    onLogout: PropTypes.func
  };

  render() {
    return <div>
      <Button variant='outlined' onClick={this.props.onLogout}>Logout</Button>
    </div>;
  }
}

export default Logout;