import { useEffect, useState } from "react";
import {
  getPendingUsers,
  approveUser,
  rejectUser,
} from "../services/authService";

import "../styles/PendingUsers.css";

function PendingUsers() {

  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState("");
  const [roleFilter, setRoleFilter] = useState("ALL");

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const data = await getPendingUsers();
      setUsers(data);
    } catch (error) {
      console.error(error);
      alert("Unable to fetch pending users.");
    }
  };

  const handleApprove = async (id) => {
    try {
      const response = await approveUser(id);

      alert(response.message);

      fetchUsers();

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Unable to approve user."
      );

    }
  };

  const handleReject = async (id) => {
    try {
      const response = await rejectUser(id);

      alert(response.message);

      fetchUsers();

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Unable to reject user."
      );

    }
  };

  const filteredUsers = users.filter((user) => {

    const matchesSearch =
      user.fullName.toLowerCase().includes(search.toLowerCase()) ||
      user.email.toLowerCase().includes(search.toLowerCase());

    const matchesRole =
      roleFilter === "ALL" ||
      user.role === roleFilter;

    return matchesSearch && matchesRole;

  });

  return (

    <div className="pending-container">

      <div className="pending-header">

        <h2>Pending User Requests</h2>

        <div className="pending-controls">

          <input
            type="text"
            placeholder="Search Users..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />

          <select
            value={roleFilter}
            onChange={(e) => setRoleFilter(e.target.value)}
          >

            <option value="ALL">All Roles</option>
            <option value="STUDENT">Student</option>
            <option value="FACULTY">Faculty</option>

          </select>

        </div>

      </div>

      <table>

        <thead>

          <tr>

            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th>Action</th>

          </tr>

        </thead>

        <tbody>

          {filteredUsers.length === 0 ? (

            <tr>

              <td colSpan="5">

                No Pending Users

              </td>

            </tr>

          ) : (

            filteredUsers.map((user) => (

              <tr key={user.id}>

                <td>{user.fullName}</td>

                <td>{user.email}</td>

                <td>{user.role}</td>

                <td>

                  <span className="status">

                    {user.status}

                  </span>

                </td>

                <td>

                  <button
                    className="approve-btn"
                    onClick={() => handleApprove(user.id)}
                  >

                    Approve

                  </button>

                  <button
                    className="reject-btn"
                    onClick={() => handleReject(user.id)}
                  >

                    Reject

                  </button>

                </td>

              </tr>

            ))

          )}

        </tbody>

      </table>

    </div>

  );
}

export default PendingUsers;