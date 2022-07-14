import axios from 'axios';
import React, { useRef, useState } from 'react';
import authenService from '../../services/authentication';

const Signup = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState('');

  const handleUsernameChange = (e: any) => {
    setUsername(e.target.value);
  };
  const handlePasswordChange = (e: any) => {
    setPassword(e.target.value);
  };
  const handleEmailChange = (e: any) => {
    setEmail(e.target.value);
  };
  const handleRoleChange = (e: any) => {
    setRole(e.target.value);
  };

  return (
    <div>
      <form onSubmit={authenService.login}>
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
        <div>
          Email
          <input
            id="email"
            type="email"
            value={email}
            name="Email"
            onChange={handleEmailChange}
          />
        </div>
        <div>
          Role
          <input
            id="password"
            type="password"
            value={password}
            name="Password"
            onChange={handlePasswordChange}
          />
        </div>
        <button id="login-button" type="submit">
          login
        </button>
      </form>
    </div>
  );
};
