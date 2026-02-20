🎵 BitStream Backend
BitStream is a high-performance RESTful API designed for modern music streaming applications. Built with a focus on scalability and security, it handles everything from user authentication to high-speed audio delivery.
🚀 Features
Secure Auth: JWT-based user authentication and role-based authorization.
Media Management: Full CRUD operations for songs, albums, and playlists.
Streaming Engine: Dedicated endpoints for low-latency audio streaming.
Smart Search: Filter and find tracks by metadata (artist, genre, title).
Playlist Control: Create, update, and manage personalized collections.
Relational Storage: Robust data persistence using PostgreSQL or MySQL.
🛠 Tech Stack
Language: Java 21+
Framework: Spring Boot 3.x
Database: PostgreSQL / MySQL
Security: Spring Security + JSON Web Tokens (JWT)
Build Tool: Maven
Version Control: Git

API Endpoints:
Endpoint        Method    Description
/users/register POST      Register a new user

/users/login    POST   Login and get JWT token

/songs          GET       Get all songs

/songs/{id}     GET       Get song by ID

/songs/play/{id} GET      Stream a song by ID

/playlists      POST     Create a new playlist

/playlists/{id} PUT       Update playlist

/playlists/{id} DELETE    Delete playlist

/search         GET      Search songs


1:Installation
Clone the repository:

git clone https://github.com/yourusername/BitStream.git
cd BitStream

2:Set up the database
Create a PostgreSQL database and update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/bitstream
spring.datasource.username=yourusername
spring.datasource.password=yourpassword

3:Run the application
mvn clean install
mvn spring-boot:run

The backend will start at http://localhost:8090.

Contributing:
We welcome contributions! Please follow these steps:
Fork the repo
Create a new branch (git checkout -b feature/YourFeature)
Commit your changes (git commit -m 'Add feature')
Push to the branch (git push origin feature/YourFeature)
Open a Pull Request

