

1. Project Overview
This project, is a simple console-based application for a (simulated) student registration system. The user can:
- List currently enrolled sections (dummy data).
- View available sections for a given course/semester (dummy data).
- Enroll in a selected course section (simulated).

2. Requirements
-Java (or higher) installed
-JDK 21
-IntelliJ IDEA** or another Java IDE for development and running.

3. Unzip and Open the project
-Download the ZIP file of this project.
-Open IntelliJ IDEA - Click on "Open", navigate to the project folder, and select it.

4. Build/Compile the Project
-In IntelliJ -after opening the project, wait for IntelliJ to import Maven dependencies (if any).
-Go to Build > Build Project. Wait till IntelliJ compile the code.

5. Run the project
-Open RegistrationClient.java
Click Run → Edit Configurations.
Click Add (+) → Application.
Set Main Class to edu.ufl.ancha.client.RegistrationClient.
Apply and Run.

6. Using the Application
- The console application will start. Enter your commands (`L`, `E`, or `Q`) when prompted.

When the app starts, you’ll see:
```
Enter L to list enrollment information for a student, E to enroll in a course, Q to Quit
```
- L: Lists enrollment information (dummy data) for a given student and semester.
  1. Enter Student Id (e.g., 10).
  2. Enter Semester id (e.g., S25).
  3. The app displays two dummy enrollment lines.

- E: Lets you see available sections and optionally enroll in one.
  1. Enter Course Id (e.g., ISM6485).
  2. Enter Semester Id (e.g., S25).
  3. The app displays two dummy available sections.
  4. You are then prompted to press E again to enroll or C to cancel:
     - If E, enter Student Id (e.g., 10) and Section Id (e.g., 1).
       The app displays a dummy success message about adding the student.
     - If C, it just cancels and returns you to the main menu.

- Q: exits the program.