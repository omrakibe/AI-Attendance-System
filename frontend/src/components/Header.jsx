import { Link } from "react-router-dom";
import "../styles/Header.css";
import { MdSchool } from "react-icons/md";

function Header() {
  return (
    <header className="header">
      <Link to="/" className="logo">
        <span className="cap">
          <MdSchool />
        </span>
        AttendAI
      </Link>
    </header>
  );
}

export default Header;