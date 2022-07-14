import axios from 'axios';
const baseUrl = '/api/v1/auth';

const login = async (credentials: any) => {
  const response = await axios.post(baseUrl + '/login', credentials);
  return response.data;
};

const signup = async (credentials: any) => {
  const response = await axios.post(baseUrl + '/signup', credentials);
  return response.data;
};

const logout = async () => {
  const response = await axios.post(baseUrl + '/logout');
  return response.data;
};
const obj = { login, signup, logout };;
export default obj;
