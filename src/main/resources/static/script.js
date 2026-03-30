const API_URL = 'http://localhost:8080/api/students';

// Load all students on page load
document.addEventListener('DOMContentLoaded', () => {
    loadStudents();
});

// Form submission
document.getElementById('student-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const studentId = document.getElementById('student-id').value;
    const student = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        course: document.getElementById('course').value,
        enrollmentDate: document.getElementById('enrollment-date').value
    };
    
    try {
        if (studentId) {
            // Update existing student
            await fetch(`${API_URL}/${studentId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(student)
            });
            alert('Student updated successfully!');
        } else {
            // Add new student
            await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(student)
            });
            alert('Student added successfully!');
        }
        
        resetForm();
        loadStudents();
    } catch (error) {
        alert('Error: ' + error.message);
    }
});

// Cancel button
document.getElementById('cancel-btn').addEventListener('click', resetForm);

// Search functionality
document.getElementById('search-btn').addEventListener('click', async () => {
    const searchTerm = document.getElementById('search-input').value;
    if (searchTerm) {
        const response = await fetch(`${API_URL}/search?name=${searchTerm}`);
        const students = await response.json();
        displayStudents(students);
    }
});

// Show all students
document.getElementById('show-all-btn').addEventListener('click', loadStudents);

// Load all students
async function loadStudents() {
    try {
        const response = await fetch(API_URL);
        const students = await response.json();
        displayStudents(students);
    } catch (error) {
        console.error('Error loading students:', error);
    }
}

// Display students in table
function displayStudents(students) {
    const tbody = document.getElementById('students-tbody');
    tbody.innerHTML = '';
    
    students.forEach(student => {
        const row = `
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.phone}</td>
                <td>${student.course}</td>
                <td>${student.enrollmentDate}</td>
                <td>
                    <button class="action-btn edit-btn" onclick="editStudent(${student.id})">Edit</button>
                    <button class="action-btn delete-btn" onclick="deleteStudent(${student.id})">Delete</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Edit student
async function editStudent(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const student = await response.json();
        
        document.getElementById('student-id').value = student.id;
        document.getElementById('name').value = student.name;
        document.getElementById('email').value = student.email;
        document.getElementById('phone').value = student.phone;
        document.getElementById('course').value = student.course;
        document.getElementById('enrollment-date').value = student.enrollmentDate;
        
        document.getElementById('form-title').textContent = 'Edit Student';
        document.getElementById('submit-btn').textContent = 'Update Student';
        document.getElementById('cancel-btn').style.display = 'inline-block';
        
        window.scrollTo({ top: 0, behavior: 'smooth' });
    } catch (error) {
        alert('Error loading student: ' + error.message);
    }
}

// Delete student
async function deleteStudent(id) {
    if (confirm('Are you sure you want to delete this student?')) {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            alert('Student deleted successfully!');
            loadStudents();
        } catch (error) {
            alert('Error deleting student: ' + error.message);
        }
    }
}

// Reset form
function resetForm() {
    document.getElementById('student-form').reset();
    document.getElementById('student-id').value = '';
    document.getElementById('form-title').textContent = 'Add New Student';
    document.getElementById('submit-btn').textContent = 'Add Student';
    document.getElementById('cancel-btn').style.display = 'none';
}