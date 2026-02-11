import React, { useEffect, useState } from "react";

const BASE_URL = "https://timetable-backend-er6g.onrender.com";

function TeacherModal({ isOpen, onClose }) {

  const [subjects, setSubjects] = useState([]);
  const [teachers, setTeachers] = useState([]);

  const [teacherId, setTeacherId] = useState("");
  const [teacherName, setTeacherName] = useState("");
  const [subjectId, setSubjectId] = useState("");

  // Load subjects when modal opens
  useEffect(() => {
    if (isOpen) {
      fetch(`${BASE_URL}/api/subjects`)
        .then(res => res.json())
        .then(data => setSubjects(data))
        .catch(err => console.error(err));
    }
  }, [isOpen]);

  const handleAddTeacher = () => {
    if (!teacherId || !teacherName || !subjectId) {
      alert("Fill all fields");
      return;
    }

    const newTeacher = {
      id: teacherId,
      name: teacherName,
      subjectId: parseInt(subjectId)
    };

    setTeachers([...teachers, newTeacher]);

    setTeacherId("");
    setTeacherName("");
  };

  const handleSaveAll = async () => {
    try {
      for (let teacher of teachers) {
        await fetch(`${BASE_URL}/api/teachers`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(teacher)
        });
      }

      alert("Teachers saved successfully");
      setTeachers([]);
      onClose();

    } catch (error) {
      console.error(error);
      alert("Error saving teachers");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-large">

        <h2>Add Teachers and Map to Subject</h2>

        {/* Teacher Inputs */}
        <div className="subject-form">

          <input
            placeholder="Teacher ID"
            value={teacherId}
            onChange={(e) => setTeacherId(e.target.value)}
          />

          <input
            placeholder="Teacher Name"
            value={teacherName}
            onChange={(e) => setTeacherName(e.target.value)}
          />

          <select
            value={subjectId}
            onChange={(e) => setSubjectId(e.target.value)}
          >
            <option value="">Select Subject</option>
            {subjects.map(sub => (
              <option key={sub.id} value={sub.id}>
                {sub.code} - {sub.name}
              </option>
            ))}
          </select>

          <button onClick={handleAddTeacher}>
            Add Teacher
          </button>

        </div>

        {/* Added Teachers List */}
        <div className="added-subjects">
          <h3>Added Teachers</h3>

          {teachers.length === 0 ? (
            <p>No teachers added</p>
          ) : (
            teachers.map((t, index) => (
              <div key={index} className="subject-item">
                {t.id} - {t.name}
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

export default TeacherModal;