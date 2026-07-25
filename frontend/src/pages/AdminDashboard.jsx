import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import DashboardCard from "../components/DashboardCard";
import { Outlet, useLocation } from "react-router-dom";
import AttendanceChart from "../components/AttendanceChart";
import RecentActivity from "../components/RecentActivity";
import {
  FaUserGraduate,
  FaChalkboardTeacher,
  FaUserClock,
  FaClipboardCheck,
} from "react-icons/fa";

import "../styles/AdminDashboard.css";

function AdminDashboard() {

  const location = useLocation();

  return (
    <div className="admin-layout">

      <Sidebar />

      <div className="admin-main">

        <Navbar />

        <div className="dashboard-content">

          {/* Show Dashboard only on /admin */}
          {location.pathname === "/admin" && (
            <>
              <div className="welcome-section">

                <h1>Welcome Back 👋</h1>

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
              <AttendanceChart />
              <RecentActivity />
            </>
          )}

          {/* Nested Pages Render Here */}
          <Outlet />

        </div>

      </div>

    </div>
  );
}

export default AdminDashboard;