let teachers = [];

function login() {
  const email = document.getElementById("email").value;
  const pass = document.getElementById("password").value;

  if (!email || !pass) {
    alert("Enter login details");
    return;
  }

  document.getElementById("loginBox").classList.add("hidden");
  document.getElementById("setupBox").classList.remove("hidden");
}

function addTeacher() {
  const name = document.getElementById("tName").value;
  const sub = document.getElementById("tSub").value;

  if (!name || !sub) return;

  teachers.push({ name, sub });

  const li = document.createElement("li");
  li.innerText = `${name} → ${sub}`;
  document.getElementById("teacherList").appendChild(li);

  document.getElementById("tName").value = "";
  document.getElementById("tSub").value = "";
}

function goToGenerator() {
  document.getElementById("setupBox").classList.add("hidden");
  document.getElementById("generateBox").classList.remove("hidden");
}