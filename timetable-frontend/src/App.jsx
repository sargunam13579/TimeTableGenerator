import { useEffect, useState } from "react";
import api from "./api/api";

function App() {
  const [timetable, setTimetable] = useState([]);

  useEffect(() => {
    api.get("/api/timetable/section/1")
      .then(res => setTimetable(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Section Timetable</h2>

      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>Day</th>
            <th>Period</th>
            <th>Subject</th>
            <th>Teacher</th>
          </tr>
        </thead>
        <tbody>
          {timetable.map((slot, i) => (
            <tr key={i}>
              <td>{slot.day}</td>
              <td>{slot.period}</td>
              <td>{slot.subject.name}</td>
              <td>{slot.teacher.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;