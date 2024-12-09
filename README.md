# Sociography - Social Media for Photographers

Sociography is a social media platform designed specifically for photographers. It allows photographers to showcase their work, interact with other photographers, and connect with potential partners and sponsors.

## Features

### 1. **User Authentication**
- **Modes:** The application supports three modes: Photographer, Partner, and Not Signed-In.
- **Authentication System:** 
  - Built using a combination of Spring Boot (backend) and React (frontend).
  - The authentication system passes the user ID to different components to fetch data from the API.
  - Utilizes Axios interceptors for managing authentication states.
  - JWT tokens are stored in local storage upon successful login or signup.

### 2. **Photographer Profiles**
- **DTOs:** A custom DTO is created to encapsulate photographer profile data, including name, description, number of followers, number of following, and pictures uploaded by the photographer.
- **Fetching Photographer Details:** The profile page fetches the photographer's name, description, number of followers, number of people the photographer is following, and the pictures uploaded by the photographer.

### 3. **Partner Profiles**
- **Details Displayed:** Name, contact number, email, profile picture, tagline, description, address, and website.
- **Conditional Rendering:** The profile picture is conditionally rendered based on availability using a lambda function.
- **Buttons:** Includes "Edit Profile" and "Contact Request" buttons with corresponding functionalities.
- **Mode-Specific Display:** Hides certain buttons if the user is in 'photographer' mode.

### 4. **Navbar Component**
- **Profile Avatar:** Displays the authenticated user's profile picture in the dropdown menu.
- **Dropdown for Types:** A dropdown next to the search bar allows users to filter by photography types (e.g., Nature, Landscape, Portrait, etc.).
- **Theme Toggle:** Includes a theme toggle switch to switch between light and dark themes.
- **Conditional Partner List:** Hides the 'Partners' list button if the user is in 'partner' mode.

### 5. **File Uploads**
- **Spring Boot Backend:**
  - Handles file uploads, stores the file details in the database, and includes the user’s email ID in the response.
  - The `Picture` entity is mapped with a `ManyToOne` relationship with the `Photographer` entity.

### 6. **Likes System**
- **Likes Entity:**
  - The `Like` entity maps likes to pictures and photographers.
  - Fetches the like count from the `Likes` table.
- **Controller:**
  - Includes endpoints for adding and removing likes and fetching the like count for a picture.
  - Like count for a picture is now dynamically fetched from the database.

### 7. **Landing Page**
- **Suggested Profile List:** Displays a suggested profile list on the left side of the landing page.

### 8. **Component Design**
- **Material UI & Joy UI:** 
  - Uses Material UI components for the core design.
  - Utilizes the `Sheet` component from `@mui/joy` for wrapping content in certain components.
  - Typography uses the "League Spartan" font for consistency.

## Project Structure

### Backend (Spring Boot)
- **Technologies Used:** 
  - Spring Boot
  - JPA
  - MySQL
  - Spring Boot DevTools
- **File Structure:**
  - **Entity Classes:** Representing database tables (`Photographer`, `Picture`, `Like`, etc.).
  - **Repository Classes:** Handling database operations.
  - **Controller Classes:** Managing REST API endpoints.
  - **Service Classes:** Business logic layer.

### Frontend (React)
- **Technologies Used:** 
  - React
  - Axios
  - Material UI (`@mui/material`)
  - Joy UI (`@mui/joy`)
- **State Management:** 
  - `AuthContext.js` file manages authentication state, including user email, mode, token, and photographerId.
  - `useAuth` hook to access authentication state across components.

## Getting Started

### Prerequisites

- Node.js and npm installed
- Java 8 or higher installed
- MySQL server running

# Sociography - Social Media for Photographers

## Backend Setup

### 1. Clone the Repository
Clone the backend repository to your local machine:
```
git clone https://github.com/vinodhariharan/sociography-backend.git
```

1.  Navigate to the backend directory:

    ```
    cd sociography-backend
    ```

3.  Configure the MySQL database in `application.properties`.
4.  Build and run the Spring Boot application:

    ```
    mvn spring-boot:run
    ```

### Frontend Setup

1.  Clone the repository:

    ```
    git clone https://github.com/vinodhariharan/sociography-frontend.git
    ```

3.  Navigate to the frontend directory:

    ```
    cd sociography-frontend
    ```

5.  Install dependencies:

    ```
    npm install
    ```

7.  Start the React application:

    ```
    npm start
    ```

### Usage

-   **Authentication:** Sign up or log in to access the platform's features.
-   **Profile Management:** Edit your profile, upload pictures, and manage your social connections.
-   **Explore:** Use the Navbar's search and dropdown options to explore different photography categories.
-   **Like System:** Like and unlike photos to engage with the community.

Contributing
------------

Contributions are welcome! Please submit a pull request or open an issue to discuss your ideas.

License
-------

This project is licensed under the MIT License.
