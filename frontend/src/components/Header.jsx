import "../styles/Header.css";
import { MdSchool } from "react-icons/md";

function Header() {
  return (
    <header className="header">
      <div className="logo">
        <span className="cap">
          <MdSchool />
        </span>
        AttendAI
      </div>
    </header>
  );
}

export default Header;