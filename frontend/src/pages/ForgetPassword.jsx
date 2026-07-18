import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../components/InputField";
import Button from "../components/Button";
import "./../styles/ForgotPassword.css";

function ForgotPassword() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");

  const handleForgotPassword = (e) => {
    e.preventDefault();

    console.log({
      email,
    });

    alert("OTP Sent Successfully");

    navigate("/reset-password");
  };

  return (
    <div className="forgot-container">
      <div className="forgot-card">

        <h1>AttendAI</h1>

        <h2>Forgot Password</h2>

        <form onSubmit={handleForgotPassword}>

          <InputField
            label="Email"
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <Button
            text="Send OTP"
            type="submit"
          />

          <p className="login-text">
            Remember your password?

            <span onClick={() => navigate("/")}>
              {" "}Login
            </span>

          </p>

        </form>

      </div>
    </div>
  );
}

export default ForgotPassword;