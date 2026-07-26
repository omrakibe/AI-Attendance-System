import axios from "axios";

const BASE_URL = "http://localhost:8081/api/subjects";

// ===============================
// Create Subject
// ===============================
export const createSubject = async (subjectData) => {
  const response = await axios.post(
    BASE_URL,
    subjectData,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }
  );

  return response.data;
};

// ===============================
// Get All Subjects
// ===============================
export const getAllSubjects = async () => {
  const response = await axios.get(BASE_URL, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });

  return response.data;
};

// ===============================
// Update Subject
// ===============================
export const updateSubject = async (id, subjectData) => {
  const response = await axios.put(
    `${BASE_URL}/${id}`,
    subjectData,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }
  );

  return response.data;
};

// ===============================
// Delete Subject
// ===============================
export const deleteSubject = async (id) => {
  const response = await axios.delete(
    `${BASE_URL}/${id}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }
  );

  return response.data;
};