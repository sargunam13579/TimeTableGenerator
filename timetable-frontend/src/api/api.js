const BASE_URL = "https://timetable-backend-er6g.onrender.com/api";

// ================= DEPARTMENT =================

export const getDepartments = async () => {
  const res = await fetch(`${BASE_URL}/departments`);
  return res.json();
};

export const createDepartment = async (data) => {
  await fetch(`${BASE_URL}/departments`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
};

// ================= SECTIONS =================

export const createSections = async (data) => {
  await fetch(`${BASE_URL}/sections/bulk`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
};

// ================= SUBJECTS =================

export const getSubjects = async () => {
  const res = await fetch(`${BASE_URL}/subjects`);
  return res.json();
};

export const createSubject = async (data) => {
  await fetch(`${BASE_URL}/subjects`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
};

// ================= TEACHERS =================

export const getTeachers = async () => {
  const res = await fetch(`${BASE_URL}/teachers`);
  return res.json();
};

export const createTeacher = async (data) => {
  await fetch(`${BASE_URL}/teachers`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
};