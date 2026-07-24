import axios from "axios";

const BASE_URL = "http://localhost:8081/api/auth";

// ===============================
// Register User
// ===============================

export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/register`, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

// ===============================
// Login User
// ===============================

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

// ===============================
// Verify OTP
// ===============================

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

// ===============================
// Forgot Password
// ===============================

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

// ===============================
// Reset Password
// ===============================

export const resetPassword = async (data) => {
  try {
    const response = await axios.post(`${BASE_URL}/reset-password`, data);

    return response.data;
  } catch (error) {
    throw error;
  }
};

// ===============================
// Get Pending Users (Admin)
// ===============================

export const getPendingUsers = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/pending-users`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

// ===============================
// Approve User
// ===============================

export const approveUser = async (id) => {
  try {
    const response = await axios.patch(
      `${BASE_URL}/approve/${id}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      }
    );

    return response.data;
  } catch (error) {
    throw error;
  }
};

// ===============================
// Reject User
// ===============================

export const rejectUser = async (id) => {
  try {
    const response = await axios.patch(
      `${BASE_URL}/reject/${id}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      }
    );

    return response.data;
  } catch (error) {
    throw error;
  }
};