const api = axios.create({
  baseURL: "https://timetable-backend-er6g.onrender.com",
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;