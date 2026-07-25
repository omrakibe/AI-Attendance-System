import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";

import InputField from "../components/InputField";
import Button from "../components/Button";
import Header from "../components/Header";

import "../styles/Login.css";
import { loginUser } from "../services/authService";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await loginUser(email, password);

      // Save JWT Token
      localStorage.setItem("token", response.token);

      // Save User Details
      localStorage.setItem("id", response.id);
      localStorage.setItem("fullName", response.fullName);
      localStorage.setItem("email", response.email);
      localStorage.setItem("role", response.role);
      localStorage.setItem("accountStatus", response.accountStatus);

      // Student Data
      if (response.role === "STUDENT") {
        localStorage.setItem("rollNumber", response.rollNumber);
      }

      // Faculty Data
      if (response.role === "FACULTY") {
        localStorage.setItem("employeeId", response.employeeId);
      }

      alert(response.message);

      console.log(response);

      // Redirect According to Role
      switch (response.role) {
        case "ADMIN":
          navigate("/admin");
          break;

        case "FACULTY":
          navigate("/faculty");
          break;

        case "STUDENT":
          navigate("/student");
          break;

        default:
          navigate("/");
      }

    } catch (error) {

      if (error.response) {
        alert(error.response.data.message);
      } else {
        alert("Unable to connect to server.");
      }

      console.error(error);
    }
  };

  return (
    <>
      <Header />

      <div className="login-container">

        <div className="login-card">

          <h2>Login</h2>

          <p className="subtitle">
            Welcome back! Sign in to continue.
          </p>

          <form onSubmit={handleLogin}>

            <InputField
              label="Email"
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />

            <div className="input-group">

              <label>Password</label>

              <div className="password-box">

                <input
                  type={showPassword ? "text" : "password"}
                  placeholder="Enter your password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />

                <span
                  className="eye-icon"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? <FaEyeSlash /> : <FaEye />}
                </span>

              </div>

            </div>

            <p
              className="forgot-password"
              onClick={() => navigate("/forgot-password")}
            >
              Forgot Password?
            </p>

            <Button
              text="Login"
              type="submit"
            />

            <p className="register-text">
              Don't have an account?{" "}
              <span onClick={() => navigate("/register")}>
                Register
              </span>
            </p>

          </form>

        </div>

      </div>
    </>
  );
}

export default Login;