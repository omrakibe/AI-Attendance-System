import {
  ResponsiveContainer,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
} from "recharts";

import "../styles/AttendanceChart.css";

const data = [
  { day: "Mon", attendance: 82 },
  { day: "Tue", attendance: 91 },
  { day: "Wed", attendance: 87 },
  { day: "Thu", attendance: 95 },
  { day: "Fri", attendance: 90 },
  { day: "Sat", attendance: 85 },
];

function AttendanceChart() {
  return (
    <div className="chart-card">

      <h2>Attendance Overview</h2>

      <ResponsiveContainer width="100%" height={320}>
        <BarChart data={data}>

          <CartesianGrid strokeDasharray="3 3" />

          <XAxis dataKey="day" />

          <YAxis />

          <Tooltip />

          <Bar
            dataKey="attendance"
            fill="#065F46"
            radius={[8, 8, 0, 0]}
          />

        </BarChart>
      </ResponsiveContainer>

    </div>
  );
}

export default AttendanceChart;