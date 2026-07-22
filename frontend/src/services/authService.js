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

// Verify OTP API
export const verifyOtp = async (email, otp) => {
  try {
    const response = await axios.post(`${BASE_URL}/verify-otp`, {
      email,
      otp,
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

// Forgot Password API
export const forgotPassword = async (email) => {
  try {
    const response = await axios.post(`${BASE_URL}/forgot-password`, {
      email,
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

// Reset Password API
export const resetPassword = async (email, otp, newPassword) => {
  try {
    const response = await axios.post(`${BASE_URL}/reset-password`, {
      email,
      otp,
      newPassword,
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};