# Hotel Management System (Java/Spring Boot)

Simple hotel management demo with REST API and lightweight web UI. Uses Spring Boot, Maven, and in-memory storage (no external DB).

## Prerequisites
- JDK 17+
- Maven 3.9+ (`mvn` on PATH)

## Run locally
```bash
./mvnw spring-boot:run
# app on http://localhost:8080
```

## Build jar
```bash
./mvnw clean package
java -jar target/hotel-management-0.0.1-SNAPSHOT.jar
```

## Jenkins pipeline
- Add the repo to a Jenkins multibranch or pipeline job.
- The included `Jenkinsfile` uses the `maven:3.9.6-eclipse-temurin-17` Docker agent to avoid managing JDK/Maven on the node.
- Stages: checkout → `mvn -B clean package` → publish JUnit (`target/surefire-reports/*.xml`) → archive `target/hotel-management-*.jar`.
- Ensure the Jenkins node can run Docker and has workspace disk space.

## API
- `GET /api/rooms` – list rooms
- `POST /api/rooms` – create room `{ name, type, nightlyRate }`
- `GET /api/guests` – list guests
- `POST /api/guests` – create guest `{ name, email, phone }`
- `GET /api/bookings` – list bookings
- `POST /api/bookings` – create booking `{ roomId, guestId, checkIn, checkOut }`
- `POST /api/bookings/{id}/cancel` – cancel booking

Validation errors return 400 with details.

## Web UI
Open `http://localhost:8080` after starting the app. The static page in `src/main/resources/static/index.html` calls the REST endpoints and shows guests, rooms, and bookings.

## Docker Deployment
```bash
# Build Docker image
docker build -t hotel-management .

# Run container
docker run -p 8080:8080 hotel-management

# Or with custom port
docker run -p 8081:8080 -e PORT=8080 hotel-management
```

## Cloud Deployment
The application reads the `PORT` environment variable for cloud platforms (Heroku, Railway, Render, etc.):
```bash
export PORT=8080
java -jar target/hotel-management-0.0.1-SNAPSHOT.jar
```

## Health Check
- `GET /api/health` - Returns application health status

## Notes
- Data is stored in memory; restart resets data. `DataInitializer` seeds sample rooms/guests.
- Port can be configured via `PORT` environment variable (defaults to 8080).
- CORS is enabled for all origins to support web UI access.
- Extendable to real DB and CI/CD as needed.

