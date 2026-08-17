# Sociography Backend

Spring Boot REST API for Sociography, a social media platform for photographers. It handles authentication, photographer/partner profiles, picture uploads, likes, comments, follows, and partner requests.

The matching client is [sociography-frontend](https://github.com/vinodhariharan/sociography-frontend); the two run as separate services connected over HTTP.

## Features

### Authentication
- JWT-based auth (`/api/auth/login`) shared across two user types: Photographer and Partner.
- Passwords are hashed with BCrypt; login compares against the hash rather than plaintext.
- Stateless sessions — every request after login carries a `Bearer` token, validated by `JwtRequestFilter`.

### Photographer & Partner Profiles
- Photographer profile DTO with name, description, follower/following counts, and uploaded pictures.
- Partner profile with contact number, email, tagline, description, address, and website.

### Pictures, Likes & Comments
- File uploads store the picture and its owning photographer (`ManyToOne` on `Picture` → `Photographer`).
- Likes and comments are scoped per picture (`/api/pictures/{pictureId}/likes`, `/api/pictures/{pictureId}/comments`), with live counts fetched from the database.

### Following & Requests
- Follow/unfollow between photographers, with an `is-following` lookup.
- `RequestController` manages contact/partnership requests with a status workflow.

## Tech Stack
- Spring Boot 3 (Java 17)
- Spring Data JPA + MySQL
- Spring Security + JWT (`jjwt`)

## Getting Started

### Prerequisites
- Java 17
- MySQL server running

### Setup
1. Clone the repository:
   ```
   git clone https://github.com/vinodhariharan/sociography-backend.git
   cd sociography-backend
   ```
2. Configure the database and JWT secret. Defaults in `application.properties` work against a local MySQL instance (`root`/`12345` on `localhost:3306/sociography`); override any of them via environment variables for anything beyond local dev:
   - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
   - `JWT_SECRET`
   - `CORS_ALLOWED_ORIGINS` — comma-separated list of frontend origins allowed to call the API (defaults to `http://localhost:3000`)
3. Run the application:
   ```
   mvn spring-boot:run
   ```
   The API listens on port `8080`.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss your ideas.

## License

This project is licensed under the MIT License.
