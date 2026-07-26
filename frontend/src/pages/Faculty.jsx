import "../styles/Faculty.css";

function Faculty() {
  return (
    <div className="faculty-container">

      <div className="faculty-header">

        <h2>Faculty Management</h2>

        <button>Add Faculty</button>

      </div>

      <div className="faculty-controls">

        <input
          type="text"
          placeholder="Search Faculty..."
        />

      </div>

      <table>

        <thead>

          <tr>

            <th>Employee ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Action</th>

          </tr>

        </thead>

        <tbody>

          <tr>

            <td>FAC001</td>
            <td>John Smith</td>
            <td>john@gmail.com</td>
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

export default Faculty;