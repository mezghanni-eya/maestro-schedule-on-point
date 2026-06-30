# 🎵 Maestro-Schedule-on-Point

## 📖 About The Project
This project is a comprehensive Java-based application designed to solve a complex, real-world operational problem: managing and scheduling the resources of a music school. 

It acts as a dual-engine system. First, it provides a robust **Database Management System (CRUD)** to handle the music school's daily operations (adding students, updating classroom equipment, managing professors). Second, it features an **Advanced Optimization Engine** that automatically generates conflict-free weekly schedules while respecting strict physical, temporal, and acoustic constraints.

## ✨ Core Features

### 1. Relational Database Management 🗄️
A fully functional CLI (Command Line Interface) to manage the music school's entities using SQLite:
* **Students:** Manage enrollments, instruments, and skill levels.
* **Professors:** Track faculty, their specializations, and availability.
* **Classrooms:** Monitor room capacities and specific musical equipment.

### 2. Smart Scheduling Optimization 🧠
Powered by **Choco-solver**, the application treats scheduling as a Constraint Satisfaction Problem (CSP). It guarantees an optimal timetable based on critical business rules:
* **Equipment Constraints:** A piano lesson *must* be assigned to a room containing a piano.
* **Acoustic/Incompatibility Constraints:** A loud percussion class cannot be scheduled adjacent to an acoustic guitar class unless the room has specific soundproofing.
* **Temporal Constraints:** A professor or student cannot be double-booked at the same time.

## 🛠️ Tech Stack
* **Language:** Java
* **Database:** SQLite & JDBC
* **Optimization Engine:** Choco-solver
* **Data Parsing:** Apache POI (for reading initial data from Excel sheets)

## 📁 Initial Project Structure
For the initial commit, this repository contains the core domain models, the main entry point, and the required external libraries.

```text
📦 maestro-schedule-on-point
 ┣ 📂 lib                  # External dependencies (Choco-solver, SQLite JDBC, Apache POI)
 ┣ 📂 src
 ┃ ┣ 📂 models             # Core Java classes (Student, Professor, Classroom)
 ┃ ┗ 📂 Main               # Application entry point and CLI menu
 ┣ 📜 README.md
 ┗ 📜 .gitignore           # Excluding compiled .class files, IDE settings, and local DBs
