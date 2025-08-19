# Attendance Scanner

## Overview
The **Attendance Scanner** is a Java-based project designed to streamline classroom attendance using student ID cards. It includes a graphical interface to display student names and track attendance in real-time.

## Features
- Import student data from CSV files.
- Scan student IDs to mark attendance.
- GUI panels to show Present and Absent students.
- Modular and object-oriented Java design.

## Project Structure
- `Runner.java` → Entry point of the program
- `Display.java` → Main GUI window and layout
- `Name.java` → Handles student names and button creation
- `NamePanel.java` → Displays buttons for Present/Absent students
- `IdScanner.java` → Reads and processes student IDs
- `Import.java` → Imports student data from files
- `README.md` → Project documentation

## Team Contributions
- **Niyath Eswar Akurati**: Developed `Display.java`, `Name.java`, `NamePanel.java`
- **Teammates**: Developed `Import.java`, `IdScanner.java`
- **Collaboration**: All team members assisted with debugging, testing, and integration.

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/NiyathAkurati/attendance-scanner.git
   
2. Compile the Java files: javac *.java
   
3.Run the program: java Runner

Example Flow: 
Launch the program via Runner.
Students scan their IDs.
Names are automatically displayed in the Present/Absent panels when a csv file is imported.
