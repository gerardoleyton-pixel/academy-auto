import axios from 'axios';

const API_URL = (import.meta.env && import.meta.env.REACT_APP_API_URL) || process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const instance = axios.create({
  baseURL: API_URL,
  headers: { 'Content-Type': 'application/json' }
});

export default instance;
