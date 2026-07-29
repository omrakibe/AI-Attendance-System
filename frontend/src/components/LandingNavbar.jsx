import { Link } from "react-router-dom";
import "../styles/LandingNavbar.css";

function LandingNavbar() {
  return (
    <header className="landing-navbar">
      <div className="logo">
        <Link to="/">AttendAI</Link>
      </div>

      <nav className="nav-links">
        <a href="#features">Features</a>
        <a href="#vision">About</a>

        <Link to="/login" className="login-btn">
          Login
        </Link>

        <Link to="/register" className="register-btn">
          Register
        </Link>
      </nav>
    </header>
  );
}

export default LandingNavbar;