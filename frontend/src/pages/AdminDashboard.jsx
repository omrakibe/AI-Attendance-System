import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import DashboardCard from "../components/DashboardCard";

import {
  FaUserGraduate,
  FaChalkboardTeacher,
  FaUserClock,
  FaClipboardCheck,
  FaUserShield,
} from "react-icons/fa";

import "../styles/AdminDashboard.css";

function AdminDashboard() {
  return (
    <div className="admin-layout">

      <Sidebar />

      <div className="admin-main">

        <Navbar />
        <div className="dashboard-content">

  <div className="welcome-section">

    <h1>Welcome Back </h1>

    <p>
      Manage students, faculty, attendance and reports from one place.
    </p>

  </div>

  <div className="dashboard-grid">

    <DashboardCard
      title="Students"
      value="75"
      icon={<FaUserGraduate />}
    />

    <DashboardCard
      title="Faculty"
      value="18"
      icon={<FaChalkboardTeacher />}
    />

    <DashboardCard
      title="Pending Users"
      value="12"
      icon={<FaUserClock />}
    />

    <DashboardCard
      title="Attendance"
      value="94%"
      icon={<FaClipboardCheck />}
    />

  </div>
  <div className="quick-actions">

  <button>Add Student</button>

  <button>Add Faculty</button>

  <button>View Reports</button>

</div>

</div>

        


      </div>

    </div>
  );
}

export default AdminDashboard;