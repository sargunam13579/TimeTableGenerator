import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import CreateDatabase from "./pages/CreateDatabase";
import "./App.css";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/create-db" element={<CreateDatabase />} />
    </Routes>
  );
}

export default App;