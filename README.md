BitStream – Music Streaming Backend

BitStream is a modern, high-performance music streaming backend designed for developers and platforms that want to manage, deliver, and stream music efficiently. Built on Java 21 and Spring Boot 3, it leverages the latest features of the Java ecosystem to provide fast, reliable, and scalable streaming services.

At its core, BitStream focuses on secure, role-based access control (RBAC), ensuring that each type of user—whether a singer, listener, or platform administrator—has precisely the permissions they need and nothing more. This design ensures both security and content integrity, making it ideal for professional music platforms.

🔐 Role-Based Access Control (RBAC)

The platform enforces three distinct roles to ensure security and content integrity:

Role	Permissions & Access

👑 Admin	Platform management, deleting any track, and user oversight.

🎤 Singer	Content creation, song uploads, and managing their own music profile.

👤 User	Personal music discovery, streaming, and managing "Liked" playlists.

🚀 API Documentation

Base Path

All song-related endpoints use the base path: /songs

Song Management

1.Method	Endpoint	Allowed Role	Description

POST	/upload	Singer	Upload raw audio files to the server.

POST	/add	Singer/Admin	Create a new song metadata entry.

PUT	/{id}	Singer/Admin	Update existing song details (Title, Genre, etc.).

DELETE	/{id}	Admin	Permanently remove a track from the library.

2.Discovery & Retrieval

Method	Endpoint	Allowed Role	Description

GET	/getallSong	All	Fetch the complete catalog of music.

GET	/search	All	Search for tracks via title, artist, or genre.

GET	/singer/{id}	All	List all tracks belonging to a specific Singer.

GET	/user/{userId}/liked	User	Retrieve a specific user's library of liked songs.

Streaming & Interactions

Method	Endpoint	Allowed Role	Description

GET	/play/{id}	User	Stream or fetch the audio stream for a track.

POST	/{id}/play	User	Increment play count and log playback activity.

POST	/{songId}/like	User	Add a track to the "Liked Songs" collection.

DELETE	/{songId}/like	User	Remove a track from the "Liked Songs" collection.

🛠️ Tech Stack & Requirements

Runtime: Java 21 (LTS)

Framework: Spring Boot 3.x

Security: Spring Security (JWT-based Authentication)

Database: PostgreSQL / MySQL (Configurable via application.properties)

Build Tool: Maven

🔧 Getting Started

Clone the Repository

git clone https://github.com/sunnybharti072006/BitStream.git

cd musicplayer

Clean and Build

./mvnw clean install

Run Locally

./mvnw spring-boot:run

📝 Notes

Ensure your database configuration in application.properties matches your local setup.

JWT authentication is required for protected endpoints.

Role-based restrictions are enforced on all critical operations.

🤝 Contributing

Fork the repository.

Create a feature branch: git checkout -b feature/my-feature

Commit your changes: git commit -m "Add my feature"

Push to the branch: git push origin feature/my-feature

Open a Pull Request.

📄 License

This project is licensed under the MIT License – see the LICENSE
 file for details.
