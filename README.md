# E-learning-online-tutoring-platform
E-Learning and Online Tutoring Platform for Software Engineering and Design Pattern Lab
# E-Learning & Online Tutoring Platform

Java Spring Boot and MySQL project for Software Engineering and Design Pattern Lab.

## Run locally

1. Create the MySQL database `elearning_platform`.
2. Keep database credentials only in `src/main/resources/application-local.properties`.
3. Run `mvn spring-boot:run`.
4. Open `http://localhost:8080`.

## Demo workflow

The application creates a local Admin account on first startup:

- Email: `admin@learntogether.local`
- Password: `Admin@123`

Register an Instructor, log in as Admin to approve the Instructor, then log in as that Instructor to create a course, lessons, and quiz questions. Publish the course as Admin. Finally, log in as a Student to enroll, complete lessons, take the quiz, and book tutoring.

## SEDP concepts used

| Concept | Location | Purpose |
|---|---|---|
| Layered architecture | `web`, `service`, `repository`, `entity` packages | Separates page requests, rules, storage, and data. |
| State pattern idea | `SlotStatus`, `CourseStatus`, `AccountStatus` | Limits an object to valid business states. |
| Strategy | `patterns/PaymentStrategy` | Supports replaceable payment methods. |
| Factory | `patterns/NotificationFactory` | Creates a notification message by type. |
| Singleton | `patterns/PlatformConfiguration` | Provides one shared configuration object. |
| Repository pattern | JPA repository interfaces | Isolates MySQL access from services. |
| Transactions and pessimistic locking | `TutoringService`, `TutoringSlotRepository` | Prevents two students booking the same tutoring slot. |

## Current modules

- Role-based registration, login, and instructor approval
- Courses, lessons, resource links, and completion percentage
- Multiple-choice quizzes with automatic scores
- Enrollment and duplicate-enrollment protection
- Course-review data model and validation rules
- Tutoring slots and protected booking
