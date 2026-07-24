import "../styles/RecentActivity.css";

const activities = [
  {
    id: 1,
    title: "Om Rakibe registered successfully",
    time: "2 minutes ago",
  },
  {
    id: 2,
    title: "John Smith approved by Admin",
    time: "15 minutes ago",
  },
  {
    id: 3,
    title: "Attendance marked for CO-3",
    time: "1 hour ago",
  },
  {
    id: 4,
    title: "Faculty account created",
    time: "Today",
  },
];

function RecentActivity() {
  return (
    <div className="activity-card">

      <h2>Recent Activity</h2>

      {activities.map((activity) => (
        <div className="activity-item" key={activity.id}>

          <div className="activity-dot"></div>

          <div>
            <h4>{activity.title}</h4>
            <p>{activity.time}</p>
          </div>

        </div>
      ))}

    </div>
  );
}

export default RecentActivity;