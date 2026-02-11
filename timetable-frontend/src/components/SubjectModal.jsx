import React, { useEffect, useState } from "react";

const BASE_URL = "https://timetable-backend-er6g.onrender.com";

function SubjectModal({ isOpen, onClose }) {

  const [departments, setDepartments] = useState([]);
  const [subjects, setSubjects] = useState([]);

  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [type, setType] = useState("");
  const [deptId, setDeptId] = useState("");

  useEffect(() => {
    if (isOpen) {
      fetch(`${BASE_URL}/api/departments`)
        .then(res => res.json())
        .then(data => setDepartments(data))
        .catch(err => console.error(err));
    }
  }, [isOpen]);

  const handleAddSubject = () => {
    if (!code || !name || !type || !deptId) {
      alert("Fill all fields");
      return;
    }

    const newSubject = {
      code,
      name,
      type,
      departmentId: parseInt(deptId)
    };

    setSubjects([...subjects, newSubject]);

    // reset fields
    setCode("");
    setName("");
    setType("");
  };

  const handleSaveAll = async () => {
    try {
      for (let subj of subjects) {
        await fetch(`${BASE_URL}/api/subjects`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(subj)
        });
      }

      alert("Subjects saved successfully");
      setSubjects([]);
      onClose();

    } catch (error) {
      console.error(error);
      alert("Error saving subjects");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-large">

        <h2>Add Subjects for Departments</h2>

        {/* Subject Inputs */}
        <div className="subject-form">

          <input
            placeholder="Subject Code"
            value={code}
            onChange={(e) => setCode(e.target.value)}
          />

          <input
            placeholder="Subject Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <select value={type} onChange={(e) => setType(e.target.value)}>
            <option value="">Select Type</option>
            <option value="theory">Theory</option>
            <option value="lab">Lab</option>
          </select>

          <select value={deptId} onChange={(e) => setDeptId(e.target.value)}>
            <option value="">Select Department</option>
            {departments.map(d => (
              <option key={d.id} value={d.id}>
                {d.id} - {d.name}
              </option>
            ))}
          </select>

          <button onClick={handleAddSubject}>
            Add Subject
          </button>

        </div>

        {/* Added Subjects List */}
        <div className="added-subjects">
          <h3>Added Subjects</h3>

          {subjects.length === 0 ? (
            <p>No subjects added</p>
          ) : (
            subjects.map((s, index) => (
              <div key={index} className="subject-item">
                {s.code} - {s.name} ({s.type})
              </div>
            ))
          )}
        </div>

        {/* Buttons */}
        <div className="modal-actions">
          <button onClick={onClose}>Cancel</button>
          <button onClick={handleSaveAll}>Save</button>
        </div>

      </div>
    </div>
  );
}

export default SubjectModal;