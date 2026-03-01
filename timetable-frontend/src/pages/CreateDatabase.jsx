import { useEffect, useState } from "react";
import {
  getDepartments,
  createDepartment,
  createSections,
  getSubjects,
  createSubject,
  getTeachers,
  createTeacher
} from "../api/api";
import "./CreateDatabase.css";

export default function CreateDatabase() {

  // ================= STATE =================

  const [departments, setDepartments] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [teachers, setTeachers] = useState([]);

  const [activePage, setActivePage] = useState("departments");

  const [showDeptPopup, setShowDeptPopup] = useState(false);
  const [showSectionPopup, setShowSectionPopup] = useState(false);
  const [showSubjectPopup, setShowSubjectPopup] = useState(false);
  const [showTeacherPopup, setShowTeacherPopup] = useState(false);

  // Department
  const [deptId, setDeptId] = useState("");
  const [deptName, setDeptName] = useState("");

  // Sections
  const [sectionDeptId, setSectionDeptId] = useState("");
  const [sectionYear, setSectionYear] = useState("");
  const [sectionCount, setSectionCount] = useState("");

  // Subject
  const [subCode, setSubCode] = useState("");
  const [subName, setSubName] = useState("");
  const [subType, setSubType] = useState("");
  const [subDeptId, setSubDeptId] = useState("");

  // Teacher
  const [teacherId, setTeacherId] = useState("");
  const [teacherName, setTeacherName] = useState("");
  const [teacherSubjectId, setTeacherSubjectId] = useState("");

  // ================= LOAD DATA =================

  useEffect(() => {
    loadAllData();
  }, []);

  const loadAllData = async () => {
    const dept = await getDepartments();
    const subj = await getSubjects();
    const teach = await getTeachers();

    setDepartments(dept);
    setSubjects(subj);
    setTeachers(teach);
  };

  // ================= SAVE FUNCTIONS =================

  const handleSaveDepartment = async () => {
    await createDepartment({ id: deptId, name: deptName });
    setShowDeptPopup(false);
    setDeptId("");
    setDeptName("");
    loadAllData();
  };

  const handleSaveSections = async () => {
    await createSections({
      departmentId: parseInt(sectionDeptId),
      year: parseInt(sectionYear),
      count: parseInt(sectionCount)
    });
    setShowSectionPopup(false);
    loadAllData();
  };

  const handleSaveSubject = async () => {
    await createSubject({
      code: subCode,
      name: subName,
      type: subType,
      departmentId: parseInt(subDeptId)
    });
    setShowSubjectPopup(false);
    loadAllData();
  };

  const handleSaveTeacher = async () => {
    await createTeacher({
      id: teacherId,
      name: teacherName,
      subjectId: parseInt(teacherSubjectId)
    });
    setShowTeacherPopup(false);
    loadAllData();
  };

  // ================= LETTER GENERATOR =================

  const generateSectionLetters = (count) => {
    const letters = [];
    for (let i = 0; i < count; i++) {
      letters.push(String.fromCharCode(65 + i)); // A, B, C
    }
    return letters;
  };

  // ================= UI =================

  return (
    <div className="page-wrapper">

      {/* Sidebar */}
      <div className="sidebar">
        <button onClick={() => setActivePage("departments")}>Departments</button>
        <button onClick={() => setActivePage("sections")}>Sections</button>
        <button onClick={() => setActivePage("subjects")}>Subjects</button>
        <button onClick={() => setActivePage("teachers")}>Teachers</button>
      </div>

      {/* Main Content */}
      <div className={`content ${
        showDeptPopup || showSectionPopup || showSubjectPopup || showTeacherPopup
          ? "blur"
          : ""
      }`}>

        {/* ================= DEPARTMENTS ================= */}
        {activePage === "departments" && (
          <>
            <button onClick={() => setShowDeptPopup(true)}>Add Dept</button>
            {departments.map((d) => (
              <div key={d.id}>{d.id} - {d.name}</div>
            ))}
          </>
        )}

        {/* ================= SECTIONS ================= */}
        {activePage === "sections" && (
          <>
            <button onClick={() => setShowSectionPopup(true)}>Add Sections</button>
            <p>Sections auto start from A and overwrite existing</p>
          </>
        )}

        {/* ================= SUBJECTS ================= */}
        {activePage === "subjects" && (
          <>
            <button onClick={() => setShowSubjectPopup(true)}>Add Subject</button>
            {subjects.map((s) => (
              <div key={s.id}>{s.code} - {s.name}</div>
            ))}
          </>
        )}

        {/* ================= TEACHERS ================= */}
        {activePage === "teachers" && (
          <>
            <button onClick={() => setShowTeacherPopup(true)}>Add Teacher</button>
            {teachers.map((t) => (
              <div key={t.id}>{t.id} - {t.name}</div>
            ))}
          </>
        )}

      </div>

      {/* ================= POPUPS ================= */}

      {showDeptPopup && (
        <div className="popup">
          <h3>Add Department</h3>
          <input placeholder="ID" value={deptId} onChange={(e)=>setDeptId(e.target.value)} />
          <input placeholder="Name" value={deptName} onChange={(e)=>setDeptName(e.target.value)} />
          <button onClick={handleSaveDepartment}>Save</button>
          <button onClick={()=>setShowDeptPopup(false)}>Cancel</button>
        </div>
      )}

      {showSectionPopup && (
        <div className="popup">
          <h3>Add Sections</h3>
          <select onChange={(e)=>setSectionDeptId(e.target.value)}>
            <option>Select Dept</option>
            {departments.map(d=>(
              <option key={d.id} value={d.id}>{d.name}</option>
            ))}
          </select>
          <input placeholder="Year" onChange={(e)=>setSectionYear(e.target.value)} />
          <input placeholder="Count" onChange={(e)=>setSectionCount(e.target.value)} />
          <button onClick={handleSaveSections}>Save</button>
          <button onClick={()=>setShowSectionPopup(false)}>Cancel</button>
        </div>
      )}

      {showSubjectPopup && (
        <div className="popup">
          <h3>Add Subject</h3>
          <input placeholder="Code" onChange={(e)=>setSubCode(e.target.value)} />
          <input placeholder="Name" onChange={(e)=>setSubName(e.target.value)} />
          <input placeholder="Type" onChange={(e)=>setSubType(e.target.value)} />
          <select onChange={(e)=>setSubDeptId(e.target.value)}>
            <option>Select Dept</option>
            {departments.map(d=>(
              <option key={d.id} value={d.id}>{d.name}</option>
            ))}
          </select>
          <button onClick={handleSaveSubject}>Save</button>
          <button onClick={()=>setShowSubjectPopup(false)}>Cancel</button>
        </div>
      )}

      {showTeacherPopup && (
        <div className="popup">
          <h3>Add Teacher</h3>
          <input placeholder="ID" onChange={(e)=>setTeacherId(e.target.value)} />
          <input placeholder="Name" onChange={(e)=>setTeacherName(e.target.value)} />
          <select onChange={(e)=>setTeacherSubjectId(e.target.value)}>
            <option>Select Subject</option>
            {subjects.map(s=>(
              <option key={s.id} value={s.id}>{s.name}</option>
            ))}
          </select>
          <button onClick={handleSaveTeacher}>Save</button>
          <button onClick={()=>setShowTeacherPopup(false)}>Cancel</button>
        </div>
      )}

    </div>
  );
}