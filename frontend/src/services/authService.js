import axios from "axios";

const BASE_URL = "http://localhost:8081/api/auth";

// Login API
export const loginUser = async (email, password) => {
  try {
    const response = await axios.post(`${BASE_URL}/login`, {
      email,
      password,
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

// Register API
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/register`, userData);

    return response.data;
  } catch (error) {
    throw error;
  }
};