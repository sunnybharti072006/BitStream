# 🚀 BitStream

<div align="center">

![GitHub stars](https://img.shields.io/github/stars/sunnybharti072006/BitStream?style=for-the-badge)
[![GitHub forks](https://img.shields.io/github/forks/sunnybharti072006/BitStream?style=for-the-badge)](https://github.com/sunnybharti072006/BitStream/network)
[![GitHub issues](https://img.shields.io/github/issues/sunnybharti072006/BitStream?style=for-the-badge)](https://github.com/sunnybharti072006/BitStream/issues)
[![GitHub license](https://img.shields.io/github/license/sunnybharti072006/BitStream?style=for-the-badge)](LICENSE)

**A robust Java Spring Boot API for managing and streaming music files.**

<!-- TODO: Add live demo link if available -->
<!-- TODO: Add documentation link (e.g., Swagger UI or external docs) -->

</div>

## 📖 Overview

BitStream is a backend service designed to handle the management and streaming of music files. Built with Java and Spring Boot, it provides a RESTful API for uploading, listing, retrieving, and streaming audio content. This project serves as a foundation for building music applications, offering core functionalities for digital music library management.

## ✨ Features

-   **Music File Upload**: Securely upload and store music files on the server.
-   **Track Listing**: Retrieve a comprehensive list of all available music tracks.
-   **Detailed Track Information**: Access metadata and details for individual music tracks.
-   **Audio Streaming**: Stream music content directly from the server.
-   **Track Deletion**: Remove specific music tracks from the system via API.

## 🛠️ Tech Stack

**Backend:**
[![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot/)
[![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

**Database:**
<!-- TODO: Detect and add specific database if configured (e.g., PostgreSQL, MySQL, H2) -->
<!-- Example for H2: [![H2 Database](https://img.shields.io/badge/H2_Database-43853D?style=for-the-badge)](https://www.h2database.com/html/main.html) -->
<!-- Example for PostgreSQL: [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/) -->
*(Configurable - typically supports relational databases)*

## 🚀 Quick Start

Follow these steps to get BitStream up and running on your local machine.

### Prerequisites
-   **Java Development Kit (JDK)**: Version 17 or higher.
-   **Apache Maven**: (Optional, as the project includes Maven Wrapper `mvnw`).
-   **Git**: For cloning the repository.
-   **Database**: (Optional, depends on `application.properties` configuration. H2 database is often used by default for Spring Boot development without explicit setup, or configure an external one like PostgreSQL/MySQL).

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/sunnybharti072006/BitStream.git
    cd BitStream
    ```

2.  **Build the project**
    Use the Maven Wrapper to build the project and download dependencies.
    ```bash
    ./mvnw clean install
    ```
    On Windows:
    ```bash
    .\mvnw.cmd clean install
    ```

3.  **Environment setup**
    Create an `application.properties` or `application.yml` file in `src/main/resources` if you need to override default settings or configure an external database.
    
    ```properties
    # src/main/resources/application.properties
    
    # Server Port
    server.port=8080
    
    # Music File Storage Path
    # Directory where music files will be stored.
    # Make sure this directory exists and the application has write permissions.
    music.storage.location=./music-files
    
    # Database Configuration (Example for H2 in-memory, if not using an external DB)
    spring.h2.console.enabled=true
    spring.datasource.url=jdbc:h2:mem:bitstreamdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto=update # Use 'create' or 'update' for schema management
    
    # For an external PostgreSQL database, you might configure:
    # spring.datasource.url=jdbc:postgresql://localhost:5432/bitstream
    # spring.datasource.username=postgres
    # spring.datasource.password=yourpassword
    # spring.jpa.hibernate.ddl-auto=update
    ```

4.  **Database setup** (if using an external database)
    If you've configured an external database like PostgreSQL or MySQL, ensure it's running and you have created the necessary database schema as specified in your `application.properties`. Spring Boot's JPA will typically handle table creation if `spring.jpa.hibernate.ddl-auto` is set to `update` or `create`.

5.  **Start development server**
    ```bash
    ./mvnw spring-boot:run
    ```
    On Windows:
    ```bash
    .\mvnw.cmd spring-boot:run
    ```

6.  **API is ready**
    The API will be running on `http://localhost:[configured_port]` (default: `http://localhost:8080`).

## 📁 Project Structure

```
BitStream/
├── .gitattributes
├── .gitignore
├── .mvn/                     # Maven Wrapper configuration files
├── music-files/              # Default directory for storing uploaded music files
├── mvnw                      # Maven Wrapper script (Linux/macOS)
├── mvnw.cmd                  # Maven Wrapper script (Windows)
├── pom.xml                   # Maven project configuration file
└── src/
    ├── main/
    │   ├── java/             # Java source code
    │   │   └── com/
    │   │       └── sunnybharti/
    │   │           └── bitstream/ # Root package for application code
    │   │               ├── controller/ # REST API controllers
    │   │               ├── service/    # Business logic services
    │   │               ├── repository/ # Data access interfaces (e.g., Spring Data JPA)
    │   │               ├── model/      # Entity classes for database mapping
    │   │               └── BitStreamApplication.java # Main Spring Boot application class
    │   └── resources/        # Application resources (e.g., application.properties/yml, static content)
    └── test/
        └── java/             # Java test code
```

## ⚙️ Configuration

### Environment Variables
While `application.properties` is commonly used, Spring Boot also supports environment variables. These typically override properties defined in `application.properties`.

| Variable | Description | Default | Required |
|----------|-------------|---------|----------|
| `SERVER_PORT` | Port for the API to listen on. | `8080` | No |
| `MUSIC_STORAGE_LOCATION` | Path to store music files. | `./music-files` | No |
| `SPRING_DATASOURCE_URL` | JDBC URL for the database. | (H2 in-memory) | Yes (for external DB) |
| `SPRING_DATASOURCE_USERNAME` | Database username. | `sa` | Yes (for external DB) |
| `SPRING_DATASOURCE_PASSWORD` | Database password. | (empty) | Yes (for external DB) |

### Configuration Files
-   `src/main/resources/application.properties`: Main configuration file for Spring Boot. Can be overridden by `application.yml`.
-   `pom.xml`: Maven's Project Object Model, defining dependencies, build process, and project metadata.

## 🔧 Development

### Available Scripts
The project uses Maven for build automation. The `mvnw` (Maven Wrapper) script ensures you use the correct Maven version without a global installation.

| Command | Description |
|---------|-------------|
| `./mvnw clean` | Cleans the target directory. |
| `./mvnw install` | Compiles source code, runs tests, and packages the project. |
| `./mvnw test` | Runs all unit and integration tests. |
| `./mvnw spring-boot:run` | Starts the Spring Boot application in development mode. |
| `./mvnw package` | Packages the project into a JAR/WAR file. |

### Development Workflow
1.  Ensure all prerequisites are installed.
2.  Clone the repository.
3.  Configure `application.properties` as needed.
4.  Start the application using `./mvnw spring-boot:run`.
5.  Use a REST client (e.g., Postman, Insomnia) to interact with the API endpoints.
6.  For code changes, the application may require a restart to pick up changes, or you can use tools like Spring DevTools for hot reloading (if configured).

## 🧪 Testing

The project uses a testing framework (likely JUnit with Spring Test).

```bash
# Run all unit and integration tests
./mvnw test
```
On Windows:
```bash
.\mvnw.cmd test
```

## 🚀 Deployment

### Production Build
To create a production-ready JAR file:

```bash
./mvnw clean package
```
This will generate an executable JAR file in the `target/` directory (e.g., `target/BitStream-0.0.1-SNAPSHOT.jar`).

### Deployment Options
-   **Executable JAR**: The generated JAR file can be run directly using `java -jar BitStream-0.0.1-SNAPSHOT.jar`. Ensure Java Runtime Environment (JRE) is installed on the server.
-   **Docker**: <!-- TODO: If a Dockerfile is added, provide Docker build and run instructions -->
-   **Cloud Platforms**: Deploy to cloud services like AWS Elastic Beanstalk, Google App Engine, Azure Spring Apps, or Heroku, which support Java applications and Spring Boot JARs.

## 📚 API Reference

The BitStream API provides endpoints for managing and streaming music. All endpoints are prefixed with `/api`.

### Base URL
`http://localhost:[port]/api` (e.g., `http://localhost:8080/api`)

### Endpoints

#### `GET /api/music`
Retrieves a list of all available music tracks.

```http
GET /api/music
```

**Response Example (200 OK):**
```json
[
  {
    "id": "uuid-1",
    "title": "Song Title 1",
    "artist": "Artist Name 1",
    "album": "Album Name 1",
    "durationSeconds": 240,
    "fileUrl": "/api/music/uuid-1/stream"
  },
  {
    "id": "uuid-2",
    "title": "Song Title 2",
    "artist": "Artist Name 2",
    "album": "Album Name 2",
    "durationSeconds": 180,
    "fileUrl": "/api/music/uuid-2/stream"
  }
]
```

#### `GET /api/music/{id}`
Retrieves details for a specific music track by its ID.

```http
GET /api/music/uuid-1
```

**Response Example (200 OK):**
```json
{
  "id": "uuid-1",
  "title": "Song Title 1",
  "artist": "Artist Name 1",
  "album": "Album Name 1",
  "genre": "Pop",
  "releaseYear": 2023,
  "durationSeconds": 240,
  "fileUrl": "/api/music/uuid-1/stream"
}
```

#### `GET /api/music/{id}/stream`
Streams the audio content of a specific music track by its ID.

```http
GET /api/music/uuid-1/stream
```

**Response:** Binary audio stream (e.g., `audio/mpeg` for MP3).

#### `POST /api/music`
Uploads a new music track to the system. Requires `multipart/form-data`.

```http
POST /api/music
Content-Type: multipart/form-data; boundary=---011000010111000001101001

---011000010111000001101001
Content-Disposition: form-data; name="file"; filename="my_new_song.mp3"
Content-Type: audio/mpeg

<binary content of my_new_song.mp3>
---011000010111000001101001--
```

**Response Example (201 Created):**
```json
{
  "id": "uuid-3",
  "title": "My New Song",
  "artist": "New Artist",
  "album": "New Album",
  "durationSeconds": 300,
  "fileUrl": "/api/music/uuid-3/stream"
}
```

#### `DELETE /api/music/{id}`
Deletes a specific music track by its ID.

```http
DELETE /api/music/uuid-1
```

**Response Example (204 No Content):**
*(Successful deletion returns no content)*

## 🤝 Contributing

We welcome contributions! Please consider the following guidelines:
1.  Fork the repository.
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

### Development Setup for Contributors
Ensure you have the prerequisites installed. Build and run tests locally before submitting a pull request.

## 📄 License

This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details. <!-- TODO: Verify actual license type and update if necessary -->

## 🙏 Acknowledgments

-   Built with Spring Boot, a powerful framework for Java applications.
-   Utilizes Apache Maven for robust project management.
-   Special thanks to the open-source community for numerous libraries and tools.

## 📞 Support & Contact

-   🐛 Issues: [GitHub Issues](https://github.com/sunnybharti072006/BitStream/issues)
-   📧 For direct inquiries, please contact [sunnybhartimailme@gmail.com] <!-- TODO: Add actual contact email -->

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ by [sunnybharti072006](https://github.com/sunnybharti072006)

</div>
