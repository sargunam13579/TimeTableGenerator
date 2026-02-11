const BASE_URL = "https://timetable-backend-er6g.onrender.com";

export async function getDepartments() {
  const res = await fetch(`${BASE_URL}/api/departments`);
  return res.json();
}

export async function createDepartment(data) {
  return fetch(`${BASE_URL}/api/departments`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });
}