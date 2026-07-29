import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../components/InputField";
import Button from "../components/Button";
import "./../styles/VerifyOtp.css";
import { verifyOtp } from "../services/authService";
import Header from "../components/Header";

function VerifyOtp() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");

  const handleVerify = async (e) => {
  e.preventDefault();

  try {
    const response = await verifyOtp(email, otp);

    alert(response.message);

    navigate("/login");

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

     <div className="verify-container">

      <div className="verify-card">

        <h2>Verify OTP</h2>
      
       <p className="subtitle">
             Enter the OTP sent to your email.
       </p>

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
</>
  ); 
}

export default VerifyOtp;