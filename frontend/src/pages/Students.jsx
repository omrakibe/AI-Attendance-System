import "../styles/Students.css";

function Students() {
  return (
    <div className="students-container">

      <div className="students-header">

        <h2>Student Management</h2>

        <button>Add Student</button>

      </div>

      <div className="students-controls">

        <input
          type="text"
          placeholder="Search Student..."
        />

      </div>

      <table>

        <thead>

          <tr>

            <th>Roll No</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Action</th>

          </tr>

        </thead>

        <tbody>

          <tr>

            <td>23CO001</td>

            <td>Om Rakibe</td>

            <td>om@gmail.com</td>

            <td>Computer</td>

            <td>

              <button className="edit-btn">Edit</button>

              <button className="delete-btn">Delete</button>

            </td>

          </tr>

          <tr>

            <td>23CO002</td>

            <td>Rahul Patil</td>

            <td>rahul@gmail.com</td>

            <td>Computer</td>

            <td>

              <button className="edit-btn">Edit</button>

              <button className="delete-btn">Delete</button>

            </td>

          </tr>

        </tbody>

      </table>

    </div>
  );
}

export default Students;