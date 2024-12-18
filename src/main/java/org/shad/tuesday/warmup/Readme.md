**Movie Management System Assignment**

**Overview**

This project provides a simple **Movie Management System** where movies can be managed using a **Data Access Object (DAO)** pattern. The goal of this task is to practice fundamental concepts of:

• **Object-Oriented Programming (OOP)**

• **Java Collections**

• **Stream API**

**Task Details**

**Classes to Work On:**

1. **Movie**:

• Represents a single movie with a title and release year.

• This class is immutable.

2. **MovieDao**:

• Provides operations to manage movies.

• Supports operations such as registering, retrieving, finding, and filtering movies.

**Steps to Complete the Task**

1. **Clone the Project**:

Clone this repository to your local machine
2. **Navigate to the Classes**:

• Locate the classes:

• org.shad.warmup.Movie

• org.shad.warmup.MovieDao

3. **Implement the Methods**:

Open the MovieDao and Movie classes, and complete the following:

**Movie Class**

• Ensure the constructor validates that the year is positive.

• Implement equals, hashCode, and toString methods.

• Provide getter methods for title and year.

• Add a factory method: Movie.of(String title, int year).

**MovieDao Class**

• Implement methods to:

• Register a movie using register(Movie movie).

• Retrieve all movies using findAll().

• Find a movie by its title: findByTitle(String title).

• Remove a movie by its title: removeByTitle(String title).

• Count the total number of movies: countMovies().

• Clear all movies: clearAll().

• Find movies released in a specific year: findMoviesByYear(int year).

4. **Run and Verify**:


**Hints for Implementation**

• Use **LinkedHashSet** to ensure insertion order and uniqueness.

• Use **streams** for operations like filtering and searching.

• Ensure validation in constructors and methods (e.g., year > 0, non-null titles).


