import { useState, useEffect } from "react";
import SubjectModal from "../components/SubjectModal";
import {
  getAllSubjects,
  deleteSubject,
} from "../services/subjectService";

import "../styles/Subjects.css";

function Subjects() {

  const [subjects, setSubjects] = useState([]);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    loadSubjects();
  }, []);

  const loadSubjects = async () => {
    try {

      const data = await getAllSubjects();

      setSubjects(data);

    } catch (error) {

      console.log(error);

      alert("Unable to fetch subjects.");

    }
  };

  const handleDelete = async (id) => {

    const confirmDelete = window.confirm(
      "Are you sure you want to delete this subject?"
    );

    if (!confirmDelete) return;

    try {

      const response = await deleteSubject(id);

      alert(response.message);

      loadSubjects();

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Unable to delete subject."
      );

    }
  };

  const filteredSubjects = subjects.filter((subject) => {

    return (

      subject.subjectCode
        .toLowerCase()
        .includes(search.toLowerCase()) ||

      subject.subjectName
        .toLowerCase()
        .includes(search.toLowerCase()) ||

      subject.department
        .toLowerCase()
        .includes(search.toLowerCase())

    );

  });

  return (

    <div className="subjects-container">

      <div className="subjects-header">

        <h2>Subject Management</h2>

        <button
          onClick={() => setShowModal(true)}
        >
          Add Subject
        </button>

      </div>

      <div className="subjects-controls">

        <input
          type="text"
          placeholder="Search Subject..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

      </div>

      <table>

        <thead>

          <tr>

            <th>Code</th>

            <th>Name</th>

            <th>Department</th>

            <th>Semester</th>

            <th>Faculty ID</th>

            <th>Action</th>

          </tr>

        </thead>

        <tbody>

          {filteredSubjects.length === 0 ? (

            <tr>

              <td colSpan="6">

                No Subjects Found

              </td>

            </tr>

          ) : (

            filteredSubjects.map((subject) => (

              <tr key={subject.id}>

                <td>{subject.subjectCode}</td>

                <td>{subject.subjectName}</td>

                <td>{subject.department}</td>

                <td>{subject.semester}</td>

                <td>{subject.facultyId}</td>

                <td>

                  <button
                    className="edit-btn"
                  >
                    Edit
                  </button>

                  <button
                    className="delete-btn"
                    onClick={() =>
                      handleDelete(subject.id)
                    }
                  >
                    Delete
                  </button>

                </td>

              </tr>

            ))

          )}

        </tbody>

      </table>

      {showModal && (

        <SubjectModal
          onClose={() => {
            setShowModal(false);
            loadSubjects();
          }}
        />

      )}

    </div>

  );

}

export default Subjects;