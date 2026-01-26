let subjectCount = 0;
// Add subject input row
function addSubject() {
    subjectCount++;
    const div = document.createElement("div");
    div.className = "subject";
    div.innerHTML = `
        <input placeholder="Subject Name" required>
        <input placeholder="Teacher" required>
        <input type="number" placeholder="Weekly Periods" required>
        <select>
            <option value="false">No Lab</option>
            <option value="true">Lab</option>
        </select>
    `;
    document.getElementById("subjects").appendChild(div);
}
// Initial subjects
for (let i = 0; i < 5; i++) addSubject();
// Handle form submit
document.getElementById("subjectForm").addEventListener("submit", async function (e) {
    e.preventDefault();
    const subjects = [];
    document.querySelectorAll(".subject").forEach(row => {
        const inputs = row.querySelectorAll("input, select");
        subjects.push({
            name: inputs[0].value,
            teacher: inputs[1].value,
            weeklyPeriods: parseInt(inputs[2].value),
            lab: inputs[3].value === "true"
        });
    });
    const res = await fetch("http://localhost:8080/generate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(subjects)
    });
    const data = await res.json();
    showResult(data);
});
// Display result
function showResult(data) {
    const div = document.getElementById("result");
    div.innerHTML = `<h3>Total Timetables: ${data.count}</h3>`;
    if (data.count === 0) {
        div.innerHTML += `<p>No valid timetable found</p>`;
        return;
    }
    const timetable = data.timetables[0]; // show first timetable
    let table = "<table><tr>";
    Object.keys(timetable).forEach(day => {
        table += `<th>${day}</th>`;
    });
    table += "</tr><tr>";
    Object.values(timetable).forEach(periods => {
        table += `<td>${periods.join("<br>")}</td>`;
    });
    table += "</tr></table>";
    div.innerHTML += table;
}