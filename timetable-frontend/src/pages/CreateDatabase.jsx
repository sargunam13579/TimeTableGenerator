import { useState, useEffect } from "react";
import { getDepartments, createDepartment, createSections } from "../api/api";
import "./CreateDatabase.css";
import SubjectModal from "../components/SubjectModal";
import TeacherModal from "../components/TeacherModal";

export default function CreateDatabase() {
  const [showDeptModal, setShowDeptModal] = useState(false);
  const [showSectionModal, setShowSectionModal] = useState(false);
  const [departments, setDepartments] = useState([]);
  const [deptId, setDeptId] = useState("");
  const [deptName, setDeptName] = useState("");
  const [selectedDept, setSelectedDept] = useState("");
  const [year, setYear] = useState("");
  const [sectionCount, setSectionCount] = useState("");
  const [showSubjectModal, setShowSubjectModal] = useState(false);
  const [showTeacherModal, setShowTeacherModal] = useState(false);

  useEffect(() => {
    loadDepartments();
  }, []);

  function loadDepartments() {
    getDepartments().then(setDepartments);
  }

  function handleSaveDepartment() {
    if (!deptId || !deptName) {
      alert("Fill all fields");
      return;
    }
    createDepartment({
      id: deptId,
      name: deptName
    }).then(() => {
      alert("Department saved");
      setDeptId("");
      setDeptName("");
      setShowDeptModal(false);
      loadDepartments();
    });
  }

  function handleSaveSections() {
    if (!selectedDept || !year || !sectionCount) {
      alert("Fill all fields");
      return;
    }
    createSections({
      departmentId: parseInt(selectedDept),
      year: parseInt(year),
      count: parseInt(sectionCount)
    }).then(() => {
      alert("Sections created successfully");
      setShowSectionModal(false);
      setSelectedDept("");
      setYear("");
      setSectionCount("");
    });
  }

  return (
    <div className="page-wrapper">

      <h2 className="title">Create Database</h2>

      <div className="actions">
        <button className="action-btn" onClick={() => setShowDeptModal(true)}>
          Add departments
        </button>
        <button className="action-btn" onClick={() => setShowSectionModal(true)}>
          Add sections
        </button>
        <button className="action-btn" onClick={() => setShowSubjectModal(true)}>
          Add subjects
        </button>
        <button className="action-btn" onClick={() => setShowTeacherModal(true)}>
          Add teachers
        </button>
      </div>

      {/* Existing Departments */}
      <div className="dept-list">
        <h3>Existing Departments</h3>

        {departments.length === 0 ? (
          <p>No departments added yet</p>
        ) : (
          departments.map((d) => (
            <div key={d.id} className="dept-item">
              {d.id} - {d.name}
            </div>
          ))
        )}
      </div>

      {/* Department Modal */}
      {showDeptModal && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Adding Department</h3>

            <div className="modal-row">
              <label>Department ID :</label>
              <input
                value={deptId}
                onChange={(e) => setDeptId(e.target.value)}
              />
            </div>

            <div className="modal-row">
              <label>Department Name :</label>
              <input
                value={deptName}
                onChange={(e) => setDeptName(e.target.value)}
              />
            </div>

            <div className="modal-actions">
              <button onClick={() => setShowDeptModal(false)}>
                Cancel
              </button>
              <button onClick={handleSaveDepartment}>
                Save
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Section Modal */}
      {showSectionModal && (
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
                {departments.map((d) => (
                  <option key={d.id} value={d.id}>
                    {d.id} - {d.name}
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
                value={sectionCount}
                onChange={(e) => setSectionCount(e.target.value)}
              />
            </div>

            <div className="modal-actions">
              <button onClick={() => setShowSectionModal(false)}>
                Cancel
              </button>
              <button onClick={handleSaveSections}>
                Save
              </button>
            </div>
          </div>
        </div>
      )}
      <SubjectModal
        isOpen={showSubjectModal}
        onClose={() => setShowSubjectModal(false)}
      />
      <TeacherModal
        isOpen={showTeacherModal}
        onClose={() => setShowTeacherModal(false)}
      />
    </div>
  );
}