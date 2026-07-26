import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/Header";
import InputField from "../components/InputField";
import Button from "../components/Button";
import "./../styles/ResetPassword.css";
import { resetPassword } from "../services/authService";

function ResetPassword() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handleResetPassword = async (e) => {

  e.preventDefault();

  try {

    const response = await resetPassword(
      email,
      otp,
      newPassword
    );

    alert(response.message);

    navigate("/");

  } catch (error) {

    if (error.response) {
      alert(error.response.data.message);
    } else {
      alert("Unable to connect to server.");
    }

  }
};

  return (
    <>
      <Header />

      <div className="reset-container">

        <div className="reset-card">

          <h2>Reset Password</h2>

          <form onSubmit={handleResetPassword}>

            <InputField
              label="Email"
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e)=>setEmail(e.target.value)}
            />

            <InputField
              label="OTP"
              type="text"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e)=>setOtp(e.target.value)}
            />

            <InputField
              label="New Password"
              type="password"
              placeholder="Enter new password"
              value={newPassword}
              onChange={(e)=>setNewPassword(e.target.value)}
            />

            <Button
              text="Reset Password"
              type="submit"
            />

          </form>

        </div>

      </div>
    </>
  );
}

export default ResetPassword;
