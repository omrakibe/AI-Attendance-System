import { useState } from "react";
import "../styles/SubjectModal.css";

function SubjectModal({ onClose }) {

  const [subjectCode, setSubjectCode] = useState("");
  const [subjectName, setSubjectName] = useState("");
  const [department, setDepartment] = useState("");
  const [semester, setSemester] = useState("");
  const [facultyId, setFacultyId] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const handleSubmit = async (e) => {
  e.preventDefault();

  const subjectData = {
    subjectCode,
    subjectName,
    department,
    semester: Number(semester),
    facultyId: Number(facultyId),
  };

  try {
    const response = await createSubject(subjectData);

    alert(response.message || "Subject created successfully.");

    onClose();

  } catch (error) {

    alert(
      error.response?.data?.message ||
      "Unable to create subject."
    );

  }
};

    alert("Subject Created Successfully");

    onClose();
  };

  return (

    <div className="modal-overlay">

      <div className="subject-modal">

        <h2>Create Subject</h2>

        <form onSubmit={handleSubmit}>

          <label>Subject Code</label>

          <input
            type="text"
            value={subjectCode}
            onChange={(e)=>setSubjectCode(e.target.value)}
            required
          />

          <label>Subject Name</label>

          <input
            type="text"
            value={subjectName}
            onChange={(e)=>setSubjectName(e.target.value)}
            required
          />

          <label>Department</label>

          <input
            type="text"
            value={department}
            onChange={(e)=>setDepartment(e.target.value)}
            required
          />

          <label>Semester</label>

          <input
            type="number"
            min="1"
            max="8"
            value={semester}
            onChange={(e)=>setSemester(e.target.value)}
            required
          />

          <label>Faculty ID</label>

          <input
            type="number"
            value={facultyId}
            onChange={(e)=>setFacultyId(e.target.value)}
            required
          />

          <div className="modal-buttons">

            <button
              type="button"
              className="cancel-btn"
              onClick={onClose}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="save-btn"
            >
              Save
            </button>

          </div>

        </form>

      </div>

    </div>

  );
}

export default SubjectModal;