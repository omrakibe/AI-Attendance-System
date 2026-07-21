import {
  FaTachometerAlt,
  FaUserGraduate,
  FaChalkboardTeacher,
  FaUserClock,
  FaClipboardList,
  FaSignOutAlt,
} from "react-icons/fa";

import { NavLink } from "react-router-dom";

import "../styles/Sidebar.css";

function Sidebar() {
  return (
    <div className="sidebar">

      <h2>AttendAI</h2>

      <NavLink to="/admin">
        <FaTachometerAlt />
        Dashboard
      </NavLink>

      <NavLink to="/admin/students">
        <FaUserGraduate />
        Students
      </NavLink>

      <NavLink to="/admin/faculty">
        <FaChalkboardTeacher />
        Faculty
      </NavLink>

      <NavLink to="/admin/pending-users">
        <FaUserClock />
        Pending Users
      </NavLink>

      <NavLink to="/admin/reports">
        <FaClipboardList />
        Reports
      </NavLink>

      <NavLink to="/">
        <FaSignOutAlt />
        Logout
      </NavLink>

    </div>
  );
}

export default Sidebar;