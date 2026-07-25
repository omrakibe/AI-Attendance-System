import "../styles/DashboardCard.css";

function DashboardCard({ title, value, icon }) {
  return (
    <div className="dashboard-card">

      <div className="card-icon">
        {icon}
      </div>

      <div>

        <h3>{title}</h3>

        <h1>{value}</h1>

      </div>

    </div>
  );
}

export default DashboardCard;