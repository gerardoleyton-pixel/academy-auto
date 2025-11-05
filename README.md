# Academy - Virtual School (skeleton)

This workspace contains a minimal, well-structured skeleton for a virtual school application.

Structure
- backend: Spring Boot (Java 11), H2 in-memory DB, REST controllers for courses and auth, JPA entities, and tests (CourseControllerTest covers GET/POST/PUT/DELETE)
- frontend: React (Parcel), axios, simple components, .env.example, and responsive CSS. Uses modals for all user alerts.

Quick start (backend)
- Asegúrate de tener Java 11+ y Gradle instalado (o usa el wrapper de Gradle).
- Desde la carpeta `backend` puedes ejecutar pruebas y arrancar la aplicación con Gradle:

Si tienes Gradle instalado globalmente:

```powershell
cd "c:\Users\gerardo.leyton\Documents\academy-auto\backend"
gradle test
gradle bootRun
```

Si prefieres usar el wrapper (recomendado), primero genera el wrapper con `gradle wrapper` y luego en Windows PowerShell:

```powershell
.\gradlew.bat test
.\gradlew.bat bootRun
```

Quick start (frontend)
- Ensure Node.js installed.
- From `frontend` run:

```powershell
npm install
npm start
```

Notes & next steps
- Passwords are stored in plain text in this demo — in production always hash and use proper authentication (JWT / Spring Security).
- The frontend includes placeholders and uses `REACT_APP_API_URL` via `.env`.
- Add more UI for admin CRUD and enrollment flows as needed.
 
Swagger / OpenAPI
- Una vez arrancada la aplicación backend (por Gradle), la documentación OpenAPI/Swagger estará disponible en:

```
http://localhost:8080/swagger-ui.html
```

Pruebas automatizadas
- Ejecuta `gradle test` (o `./gradlew.bat test` en Windows si usas el wrapper) desde la carpeta `backend` para ejecutar los tests JUnit que cubren CRUD (GET/POST/PUT/DELETE) para cursos, usuarios y matriculaciones.

