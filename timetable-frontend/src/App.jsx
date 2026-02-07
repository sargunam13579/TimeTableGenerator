import { useEffect, useState } from "react";
import api from "./api/api";

function PrivateRoute({ children }) {
  return localStorage.getItem("token")
    ? children
    : window.location.href = "/login";
}

export default App;