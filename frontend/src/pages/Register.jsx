import { useNavigate } from "react-router-dom";
import "./../styles/Register.css";
import { useState } from "react";
import { registerUser } from "../services/authService";
import InputField from "../components/InputField";
import Button from "../components/Button";



function Register() {


  const navigate = useNavigate();
  

  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("STUDENT");
   
  const handleRegister = async (e) => {
  e.preventDefault();

  const userData = {
    fullName,
    email,
    password,
    role,
  };

  try {
    const response = await registerUser(userData);

    alert(response.message);

    navigate("/verify-otp");

  } catch (error) {

    if (error.response) {
      alert(error.response.data.message);
    } else {
      alert("Unable to connect to server.");
    }
  }
};
  return (
    <div className="register-container">

      <div className="register-card">

        <h1>AttendAI</h1>

        <h2>Register</h2>

       <form onSubmit={handleRegister}>

  <label>Full Name</label>
  <input
    type="text"
    placeholder="Enter your full name"
    value={fullName}
    onChange={(e) => setFullName(e.target.value)}
  />

  <label>Email</label>
  <input
    type="email"
    placeholder="Enter your email"
    value={email}
    onChange={(e) => setEmail(e.target.value)}
  />

  <label>Password</label>
  <input
    type="password"
    placeholder="Enter your password"
    value={password}
    onChange={(e) => setPassword(e.target.value)}
  />

  <label>Role</label>

  <select
    value={role}
    onChange={(e) => setRole(e.target.value)}
  >
    <option value="STUDENT">STUDENT</option>
    <option value="FACULTY">FACULTY</option>
  </select>

  <Button
  text="Register"
  type="submit"
/>

  <p className="login-text">
    Already have an account?
    <span onClick={() => navigate("/")}>
      {" "}Login
    </span>
  </p>

</form>

      </div>

    </div>
  );
}

export default Register;