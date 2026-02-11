import { useState, useEffect } from "react";
import { getDepartments, createDepartment } from "../api/api";
import "./CreateDatabase.css";

export default function CreateDatabase() {
  const [showSetup, setShowSetup] = useState(true);
  const [showDeptModal, setShowDeptModal] = useState(false);

  const [departments, setDepartments] = useState([]);
  const [deptId, setDeptId] = useState("");
  const [deptName, setDeptName] = useState("");

  useEffect(() => {
    loadDepartments();
  }, []);

  function loadDepartments() {
    getDepartments().then(setDepartments);
  }

  function handleSaveDepartment() {
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

  return (
    <div className="page-wrapper">
      <h2 className="title">Add departments</h2>

      <div className="actions">
        <button className="action-btn" onClick={() => setShowDeptModal(true)}>
          Add departments
        </button>
      </div>

      {/* Already Added Departments */}
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

      {/* MODAL */}
      {showDeptModal && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Adding department</h3>

            <div className="modal-row">
              <label>Department id :</label>
              <input
                value={deptId}
                onChange={(e) => setDeptId(e.target.value)}
              />
            </div>

            <div className="modal-row">
              <label>Department name :</label>
              <input
                value={deptName}
                onChange={(e) => setDeptName(e.target.value)}
              />
            </div>

            <div className="modal-actions">
              <button onClick={() => setShowDeptModal(false)}>
                cancel
              </button>
              <button onClick={handleSaveDepartment}>
                save
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}