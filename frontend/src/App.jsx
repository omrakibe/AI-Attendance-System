import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import VerifyOtp from "./pages/VerifyOtp";
import ForgetPassword from "./pages/ForgetPassword";
import ResetPassword from "./pages/ResetPassword";
import AdminDashboard from "./pages/AdminDashboard";
import PendingUsers from "./pages/PendingUsers";
import NotFound from "./pages/NotFound";
import Students from "./pages/Students";
import Faculty from "./pages/Faculty";

function App() {
  return (
    <Routes>

      <Route path="/" element={<Login />} />

      <Route path="/register" element={<Register />} />

      <Route path="/verify-otp" element={<VerifyOtp />} />

      <Route path="/forgot-password" element={<ForgetPassword />} />

      <Route path="/reset-password" element={<ResetPassword />} />

      {/* Admin Routes */}
      <Route path="/admin" element={<AdminDashboard />}>

        <Route
          index
          element={<h2>Welcome to Admin Dashboard</h2>}
        />
        <Route
    path="students"
    element={<Students />}
/>      



        <Route
          path="pending-users"
          element={<PendingUsers />}
        />
        <Route
    path="faculty"
    element={<Faculty />}
/>

      </Route>

      <Route path="*" element={<NotFound />} />

    </Routes>
  );
}

export default App;