import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../components/InputField";
import Button from "../components/Button";
import "../styles/ForgotPassword.css";
import Header from "../components/Header";
import "../styles/Header.css";
import { forgotPassword } from "../services/authService";

function ForgotPassword() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");

  const handleForgotPassword = async (e) => {
     e.preventDefault();

     try {
        const response = await forgotPassword(email);

        alert(response.message);

        navigate("/reset-password");

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

      <div className="login-container">
         <div className="forgot-container">
            <div className="forgot-card">

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

                        <span onClick={() => navigate("/login")}>
                            {" "}Login
                        </span>

                      </p>

                </form>

            </div>
          </div>
       </div>
    </>
  );   
}

export default ForgotPassword;