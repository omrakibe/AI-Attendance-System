import LandingNavbar from "../components/LandingNavbar";
import "../styles/LandingPage.css";
import { Link } from "react-router-dom";

function LandingPage() {
  return (
    <div className="landing-page">

      <LandingNavbar />

      {/* Hero Section */}
      <section className="hero">

        <div className="hero-left">

          <h1>
            Smarter Attendance.
            <br />
            Powered by AI.
          </h1>

          <p>
            AttendAI is an intelligent attendance management platform
            designed to simplify attendance using Artificial Intelligence,
            modern microservices, and real-time analytics.
          </p>

          <div className="hero-buttons">

            <Link to="/register" className="primary-btn">
                Get Started
            </Link>

            <a href="#features" className="secondary-btn">
                Learn More
            </a>

        </div>

        </div>

        <div className="hero-right">

          <div className="hero-image">
            AI Illustration Coming Soon
          </div>

        </div>

      </section>

      {/* Features */}

      <section id="features" className="features">

          <h2>Why AttendAI?</h2>

          <p className="section-description">
              Intelligent features designed to simplify attendance management
              using Artificial Intelligence and modern cloud technologies.
          </p>

          <div className="feature-grid">

            <div className="feature-card">
              <div className="feature-icon">🤖</div>
              <h3>AI Attendance</h3>
              <p>
                Faculty can mark attendance using natural language commands.
              </p>
            </div>

            <div className="feature-card">
              <div className="feature-icon">📊</div>
              <h3>Analytics</h3>
              <p>
                Track attendance subject-wise with real-time insights.
              </p>
            </div>

            <div className="feature-card">
              <div className="feature-icon">🎓</div>
              <h3>Student Dashboard</h3>
              <p>
                Students can monitor attendance and academic progress.
              </p>
            </div>

            <div className="feature-card">
              <div className="feature-icon">👨‍🏫</div>
              <h3>Faculty Dashboard</h3>
              <p>
                Manage classes, attendance, and reports effortlessly.
              </p>
            </div>

            <div className="feature-card">
              <div className="feature-icon">📧</div>
              <h3>Weekly Reports</h3>
              <p>
                Automated attendance reports sent directly to email.
              </p>
            </div>

            <div className="feature-card">
              <div className="feature-icon">🔐</div>
              <h3>Secure Access</h3>
              <p>
                Role-based authentication for Admin, Faculty, and Students.
              </p>
    </div>

  </div>

</section>

      {/* AI Demo */}

<section id="ai-demo" className="ai-demo">

  <h2>AI in Action</h2>

  <p className="section-description">
    AttendAI understands natural language and performs attendance
    operations instantly for both faculty and students.
  </p>

  <div className="chat-container">

    {/* Faculty Chat */}
    <div className="chat-card">

      <h3>👨‍🏫 Faculty Assistant</h3>

      <div className="message user">
        Mark roll numbers 10 to 20 as present and the remaining students absent.
      </div>

      <div className="message ai">
        ✅ Attendance marked successfully.<br />
        Present: Roll 10–20<br />
        Absent: Remaining Students
      </div>

    </div>

    {/* Student Chat */}
    <div className="chat-card">

      <h3>🎓 Student Assistant</h3>

      <div className="message user">
        How many more lectures do I need to attend to reach 75% attendance?
      </div>

      <div className="message ai">
        📊 You need to attend 8 more lectures to reach 75% overall attendance.
      </div>

    </div>

  </div>

</section>

      {/* Technology Stack */}

<section id="tech-stack" className="tech-stack">

  <h2>Technology Stack</h2>

  <p className="section-description">
    AttendAI is built using modern technologies that ensure
    scalability, security, and high performance.
  </p>

  <div className="tech-grid">

    <div className="tech-card">
      <span>⚛️</span>
      <h3>React</h3>
    </div>

    <div className="tech-card">
      <span>☕</span>
      <h3>Spring Boot</h3>
    </div>

    <div className="tech-card">
      <span>🟦</span>
      <h3>Java</h3>
    </div>

    <div className="tech-card">
      <span>🐬</span>
      <h3>MySQL</h3>
    </div>

    <div className="tech-card">
      <span>🐳</span>
      <h3>Docker</h3>
    </div>

    <div className="tech-card">
      <span>🔐</span>
      <h3>JWT</h3>
    </div>

    <div className="tech-card">
      <span>☁️</span>
      <h3>Microservices</h3>
    </div>

    <div className="tech-card">
      <span>🤖</span>
      <h3>Artificial Intelligence</h3>
    </div>

  </div>

</section>

      {/* Vision */}

<section id="vision" className="vision">

  <div className="vision-content">

    <h2>Our Vision</h2>

    <p>
      Our goal is to make attendance management intelligent,
      efficient, and user-friendly by reducing manual effort
      and enabling institutions to leverage Artificial Intelligence
      for better academic administration.
    </p>

    <p>
      AttendAI combines AI, Microservices, and secure authentication
      to deliver a modern attendance management platform that helps
      administrators, faculty, and students work more efficiently.
    </p>

    <div className="vision-highlights">

      <div className="highlight-card">
        <h3>🤖 AI Powered</h3>
        <p>
          Natural language attendance marking and AI assistance.
        </p>
      </div>

      <div className="highlight-card">
        <h3>⚡ Real-Time Insights</h3>
        <p>
          Instant attendance reports and analytics.
        </p>
      </div>

      <div className="highlight-card">
        <h3>🔒 Secure Platform</h3>
        <p>
          JWT authentication with role-based access.
        </p>
      </div>

    </div>

  </div>

</section>

      <footer className="footer">

  <h2>AttendAI</h2>

  <p>
    Smarter Attendance. Powered by AI.
  </p>

  <div className="footer-links">

    <a href="#features">Features</a>

    <a href="#vision">About</a>

    <Link to="/login">Login</Link>

    <Link to="/register">Register</Link>

  </div>

  <p className="copyright">
    © 2026 AttendAI. All Rights Reserved.
  </p>

</footer>

    </div>
  );
}

export default LandingPage;