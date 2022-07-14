import React, { useState } from 'react';
import authenService from '../../services/authentication';

const Signup = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState('ROLE_CUSTOMER');

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

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log(role);
    authenService.signup({ username, password, email, role });
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
        Role:
        <select id="role" onChange={handleRoleChange} value={role}>
          <option value="ROLE_CUSTOMER">Customer</option>
          <option value="ROLE_VENDOR">Admin</option>
        </select>
        <button id="login-button" type="submit">
          Signup
        </button>
      </form>
    </div>
  );
};

export default Signup;
