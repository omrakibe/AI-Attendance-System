import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../components/InputField";
import Button from "../components/Button";
import "./../styles/VerifyOtp.css";

function VerifyOtp() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");

  const handleVerify = (e) => {
    e.preventDefault();

    console.log({
      email,
      otp,
    });

    alert("OTP Verified Successfully");

    navigate("/");
  };

  return (
    <div className="verify-container">

      <div className="verify-card">

        <h1>AttendAI</h1>

        <h2>Verify OTP</h2>

        <form onSubmit={handleVerify}>

          <InputField
            label="Email"
            type="email"
            placeholder="Enter your Email"
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

          <Button
            text="Verify OTP"
            type="submit"
          />

        </form>

      </div>

    </div>
  );
}

export default VerifyOtp;