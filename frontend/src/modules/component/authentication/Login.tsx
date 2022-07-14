import React, { useRef, useState } from 'react';
import authenService from '../../services/authentication';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (e: any) => {
    setUsername(e.target.value);
  };
  const handlePasswordChange = (e: any) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log(e.target.value);
    authenService.login({username, password});
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          Username
          <input
            id="username"
            type="text"
            value={username}
            name="Username"
            onChange={handleUsernameChange}
          />
        </div>
        <div>
          Password
          <input
            id="password"
            type="password"
            value={password}
            name="Password"
            onChange={handlePasswordChange}
          />
        </div>
        <button id="login-button" type="submit">
          Login
        </button>
        <button id="login-button" onClick={authenService.logout}>
          Logout
        </button>
      </form>
    </div>
  );
}

export default Login;