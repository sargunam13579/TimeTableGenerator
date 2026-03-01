import { useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/create-db");
  };

  return (
    <div className="login-container">
      <h2>Timetable Generator</h2>
      <button onClick={handleLogin}>Create / Edit Database</button>
    </div>
  );
}