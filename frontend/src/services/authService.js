import axios from "axios";

const BASE_URL = "http://localhost:8081/api/auth";

// ===============================
// Register User
// ===============================
export const registerUser = async (userData) => {
  const response = await axios.post(`${BASE_URL}/register`, userData);
  return response.data;
};

// ===============================
// Login User
// ===============================
export const loginUser = async (email, password) => {
  const response = await axios.post(`${BASE_URL}/login`, {
    email,
    password,
  });

  return response.data;
};

// ===============================
// Verify OTP
// ===============================
export const verifyOtp = async (email, otp) => {
  const response = await axios.post(`${BASE_URL}/verify-otp`, {
    email,
    otp,
  });

  return response.data;
};

// ===============================
// Forgot Password
// ===============================
export const forgotPassword = async (email) => {
  const response = await axios.post(`${BASE_URL}/forgot-password`, {
    email,
  });

  return response.data;
};

// ===============================
// Reset Password
// ===============================
export const resetPassword = async (email, otp, newPassword) => {
  const response = await axios.post(`${BASE_URL}/reset-password`, {
    email,
    otp,
    newPassword,
  });

  return response.data;
};

// ===============================
// Get Pending Users (Admin)
// ===============================
export const getPendingUsers = async () => {
  const response = await axios.get(`${BASE_URL}/pending-users`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });

  return response.data;
};

// ===============================
// Approve User (Admin)
// ===============================
export const approveUser = async (id) => {
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
};

// ===============================
// Reject User (Admin)
// ===============================
export const rejectUser = async (id) => {
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
};