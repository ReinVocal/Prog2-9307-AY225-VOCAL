// Programmer Identifier: Rein Alexander A. Vocal 25-1823-197
// Student Record System - JavaScript (Chrome Hearts ASCII Hacker Theme)

const csvData = `StudentID,first_name,last_name,LABWORK1,LABWORK2,LABWORK3,PrelimExam,Attendance
073900438,Osbourne,Wakenshaw,69,72,80,85,90
114924014,Albie,Gierardi,58,60,65,70,75
111901632,Eleen,Pentony,43,50,55,60,65
084000084,Arie,Okenden,31,40,45,50,55
272471551,Alica,Muckley,49,55,60,65,70`;

let students = [];

function parseCSV(data) {
  const lines = data.trim().split("\n");
  for (let i = 1; i < lines.length; i++) {
    const parts = lines[i].split(",");
    students.push({
      id: parts[0],
      name: parts[1] + " " + parts[2],
      lab1: parts[3],
      lab2: parts[4],
      lab3: parts[5],
      prelim: parts[6],
      attendance: parts[7]
    });
  }
}

function render() {
  const table = document.getElementById("studentTable");
  table.innerHTML = `
    <tr>
      <th>ID</th><th>Name</th>
      <th>Labwork 1</th><th>Labwork 2</th><th>Labwork 3</th>
      <th>Prelim Exam</th><th>Attendance</th><th>Action</th>
    </tr>`;
  students.forEach((s, index) => {
    table.innerHTML += `
      <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.lab1}</td>
        <td>${s.lab2}</td>
        <td>${s.lab3}</td>
        <td>${s.prelim}</td>
        <td>${s.attendance}</td>
        <td><button onclick="deleteStudent(${index})">✖ Delete</button></td>
      </tr>`;
  });
}

document.getElementById("addForm").addEventListener("submit", function(e) {
  e.preventDefault();
  const id = document.getElementById("id").value;
  const name = document.getElementById("name").value;
  const lab1 = document.getElementById("lab1").value;
  const lab2 = document.getElementById("lab2").value;
  const lab3 = document.getElementById("lab3").value;
  const prelim = document.getElementById("prelim").value;
  const attendance = document.getElementById("attendance").value;

  if (id && name) {
    students.push({ id, name, lab1, lab2, lab3, prelim, attendance });
    render();
    document.getElementById("id").value = "";
    document.getElementById("name").value = "";
    document.getElementById("lab1").value = "";
    document.getElementById("lab2").value = "";
    document.getElementById("lab3").value = "";
    document.getElementById("prelim").value = "";
    document.getElementById("attendance").value = "";
  }
});

function deleteStudent(index) {
  students.splice(index, 1);
  render();
}

// Init
parseCSV(csvData);
render();

