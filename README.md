# 📚 Book Scout

**Book Scout** is an open-source application designed for book lovers who aren’t sure what to read next. Simply choose a category that interests you, and Book Scout will provide a list of relevant books with short descriptions. You can decide whether you're interested in a book by reading its summary, and if you are, tap for more details. This project consists of a **React frontend** and a **Java Spring Boot backend** integrated with the **Google Books API**.

## Table of Contents
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Architecture Overview](#architecture-overview)
- [Contributing](#contributing)
- [License](#license)

## Tech Stack 🛠️

### Frontend
- **React** with **Vite** as the build tool
- **React Bootstrap** for responsive and attractive UI components
- **Axios** for making requests to the backend API

### Backend
- **Java Spring Boot** for REST API services
- **Maven** as the build automation tool
- **JUnit5** for unit testing
- Uses **Google Books API** to fetch book information and prepopulate the database

### Database
- **MySQL** for storing books, categories, and user progress (`books`, `categories`, and `categories_progress` tables)

### Containerization
- **Docker** to containerize the backend
- **Docker Compose** for easy setup and running of both the backend and the database

## Features ✨
- Browse books by category if you’re not sure what to read next.
- Read a short description of each book to decide whether it’s interesting to you.
- Tap on a book to see detailed information (author, title, number of pages, publication date, etc.).
- Keep swiping until you find a book that fits your interest!

## Getting Started 🚀

### Prerequisites
To run this application locally, you will need:

- **Node.js** and **npm** installed
- **Docker** and **Docker Compose**

### Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/rubinnum/book-scout.git
    cd book-scout
    ```

2. **Backend Setup**:
    - Navigate to the backend directory.
    - Create a `.env` file using the `.env.example` provided. The `.env` file should contain:
      ```env
      GOOGLE_BOOKS_API_KEY=your_google_books_api_key_here
      ```
    - You can obtain a Google Books API key from the [Google Developer Console](https://console.developers.google.com/).

3. **Frontend Setup**:
    - Navigate to the frontend directory.
    - Install dependencies:
      ```bash
      npm install
      ```

### Running the Application

1. **Start Backend and Database**:
    - Make sure you're in the backend directory and run:
      ```bash
      docker-compose up
      ```
    - This will start the backend server and the MySQL database.

2. **Start Frontend**:
    - Navigate to the frontend directory and run:
      ```bash
      npm run dev
      ```
    - The frontend will usually start on port `5173`. Open your browser and navigate to [http://localhost:5173](http://localhost:5173).

## Architecture Overview 🏗️

The **Book Scout** app follows a **modular architecture**:

- **Frontend**: Built with React, using **React Bootstrap** for a polished UI and **Axios** for API communication. The frontend communicates with the backend REST API to retrieve books by category and update user progress.
- **Backend**: Built with **Spring Boot**, following a layered architecture:
  - **API Layer**: Handles incoming requests and routes them appropriately.
  - **Business Logic Layer**: Processes data and implements the application's logic.
  - **Database Layer**: Interacts with the **MySQL** database for CRUD operations.
- **Database**: Stores information about books, categories, and user progress.

The backend also integrates with the **Google Books API** to fetch books related to specific categories, which are then stored in the local database for easy access.

## Contributing 🤝

We welcome contributions! To get started:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeatureName`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/YourFeatureName`).
6. Open a pull request.

Feel free to open any issues or provide suggestions to improve **Book Scout**.

## License 📄

This project is open-source and available under the [MIT License](LICENSE).

---

Thank you for checking out **Book Scout**! If you enjoy using this app or have any feedback, we'd love to hear from you. Happy reading! 📖✨
