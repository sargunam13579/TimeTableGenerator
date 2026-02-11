import React, { useEffect, useState } from "react";

const BASE_URL = "https://timetable-backend-er6g.onrender.com";

function SectionModal({ isOpen, onClose }) {
  const [departments, setDepartments] = useState([]);
  const [selectedDept, setSelectedDept] = useState("");
  const [year, setYear] = useState("");
  const [count, setCount] = useState("");

  // Load departments when modal opens
  useEffect(() => {
    if (isOpen) {
      fetch(`${BASE_URL}/api/departments`)
        .then((res) => res.json())
        .then((data) => setDepartments(data))
        .catch((err) => console.error(err));
    }
  }, [isOpen]);

  const handleSave = async () => {
    if (!selectedDept || !year || !count) {
      alert("Fill all fields");
      return;
    }

    try {
      const response = await fetch(`${BASE_URL}/api/sections/bulk`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          departmentId: parseInt(selectedDept),
          year: parseInt(year),
          count: parseInt(count)
        })
      });

      if (response.ok) {
        alert("Sections created successfully");
        setSelectedDept("");
        setYear("");
        setCount("");
        onClose();
      } else {
        alert("Error creating sections");
      }

    } catch (error) {
      console.error("Error:", error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h3>Add Sections</h3>

        <div className="modal-row">
          <label>Department :</label>
          <select
            value={selectedDept}
            onChange={(e) => setSelectedDept(e.target.value)}
          >
            <option value="">Select</option>
            {departments.map((dept) => (
              <option key={dept.id} value={dept.id}>
                {dept.id} - {dept.name}
              </option>
            ))}
          </select>
        </div>

        <div className="modal-row">
          <label>Year :</label>
          <select value={year} onChange={(e) => setYear(e.target.value)}>
            <option value="">Select</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
          </select>
        </div>

        <div className="modal-row">
          <label>Section Count :</label>
          <input
            type="number"
            min="1"
            value={count}
            onChange={(e) => setCount(e.target.value)}
          />
        </div>

        <div className="modal-actions">
          <button onClick={onClose}>Cancel</button>
          <button onClick={handleSave}>Save</button>
        </div>
      </div>
    </div>
  );
}

export default SectionModal;