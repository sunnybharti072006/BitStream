🎵 BitStream Backend

BitStream is a high-performance RESTful API designed for modern music streaming applications. Built for scalability and security, it handles everything from user authentication to high-speed audio delivery. 🚀

🛠 Tech Stack

Language: Java 21+

Framework: Spring Boot 3.x

Database: PostgreSQL / MySQL

Security: Spring Security + JWT

Build Tool: Maven

Version Control: Git

🌳 Project Feature Tree

BitStream Backend

├─ Authentication & Security

│  ├─ User Registration (JWT)

│  ├─ User Login (JWT Token)

│  └─ Role-Based Authorization


├─ Songs Module

│  ├─ Get All Songs

│  ├─ Get Song By ID

│  └─ Stream Song (Low Latency)


├─ Playlist Module

│  ├─Create Playlist

│  ├─ Update Playlist

│  └─ Delete Playlist


├─ Search Engine

│  └─ Search Songs (Title / Artist / Genre)


├─ Database Layer

│  ├─ PostgreSQL Support

│  └─ MySQL Support


└─ Pending Features

   ├─ Like / Favorite System

   ├─ Next / Previous Track Loop

   ├─ Album Artwork Upload

   ├─ Recommendation Engine

   └─ User Listening History



✅ Completed Features

Authentication & User Management

Secure JWT-based authentication

Role-based authorization

Media Management

CRUD operations for songs, albums, and playlists

Streaming Engine

Low-latency audio streaming endpoints

Smart Search

Search/filter songs by artist, genre, or title

Playlist Control

Create, update, delete, and manage personalized playlists

Relational Storage

Robust data persistence with PostgreSQL or MySQL

⏳ Pending Features

Implement like/favorite system for songs and playlists

Add next/previous track looping for playlists

Support for album artwork upload

Add user activity logging for analytics

Implement recommendation engine based on listening history

| Method | Endpoint                | Description                             |
| ------ | ----------------------- | --------------------------------------- |
| POST   | `/users/register`       | Register a new user                     |
| POST   | `/users/login`          | Login user and get JWT token            |
| GET    | `/songs`                | Get all songs                           |
| GET    | `/songs/{id}`           | Get song by ID                          |
| GET    | `/songs/play/{id}`      | Stream song by ID                       |
| POST   | `/playlists`            | Create new playlist                     |
| PUT    | `/playlists/{id}`       | Update playlist                         |
| DELETE | `/playlists/{id}`       | Delete playlist                         |
| GET    | `/search?query=keyword` | Search songs by title, artist, or genre |


💻 Installation

1.Clone the repository:

git clone https://github.com/yourusername/BitStream.git

cd BitStream

2.Set up the database:

Create a PostgreSQL database and update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/bitstream

spring.datasource.username=yourusername

spring.datasource.password=yourpassword

3.Run the application:

mvn clean install

mvn spring-boot:run

🤝 Contributing

We welcome contributions!

Fork the repo

Create a new branch

git checkout -b feature/YourFeature

Commit your changes

git commit -m "Add feature"

Push to your branch

git push origin feature/YourFeature

Open a Pull Request


