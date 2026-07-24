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
      console.log(error);
    }
  };

  const handleApprove = async (id) => {
    await approveUser(id);
    fetchUsers();
  };

  const handleReject = async (id) => {
    await rejectUser(id);
    fetchUsers();
  };

  const filteredUsers = users.filter((user) => {
    const matchesSearch =
      user.fullName.toLowerCase().includes(search.toLowerCase()) ||
      user.email.toLowerCase().includes(search.toLowerCase());

    const matchesRole =
      roleFilter === "ALL" || user.role === roleFilter;

    return matchesSearch && matchesRole;
  });

  return (
    <div className="pending-container">

      <div className="pending-header">

        <h2>Pending User Requests</h2>

        <div className="pending-controls">

          <input
            type="text"
            placeholder="Search users..."
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
            <th>ID</th>
            <th>Status</th>
            <th>Action</th>

          </tr>

        </thead>

        <tbody>

          {filteredUsers.length === 0 ? (

            <tr>

              <td colSpan="6">

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

                  {user.role === "STUDENT"
                    ? user.rollNumber
                    : user.employeeId}

                </td>

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