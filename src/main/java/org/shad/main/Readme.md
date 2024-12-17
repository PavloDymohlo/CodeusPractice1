**Movie Management System - Part 2**

**Overview**

This part builds upon the **Movie Management System** by extending its functionality. You will implement the UpgradeMovieService class, which introduces additional capabilities to **sort** and **filter** movies based on various criteria.

The project focuses on practicing:

• **Advanced Stream API** usage

• **Comparators for sorting**

• **Extending classes** to promote code reuse (inheritance)

**Task Details**

**Classes to Work On:**

1. **MovieService**;
2. **MovieValidator**;
3. **UpgradeMovieService**;

• Adds new functionalities:

• Sorting movies by title or year (ascending/descending order)

• Filtering movies by title prefix

• Filtering movies by a year range


**Steps to Complete the Task**

1. **Navigate to the Classes**:

• Locate the file:

• org.shad.main.UpgradeMovieService
• org.shad.main.MovieService
• org.shad.main.MovieValidator

3. **Implement the Methods**
4. Run and verify with tests.



**Hints for Implementation if need**

1. **Sorting**:

• Use Comparator with Comparator.comparing() for title or year.

• Reverse the comparator if sorting in descending order.

2. **Filtering by Prefix**:

• Use String.toLowerCase() to ensure case-insensitivity.

• Use String.startsWith() for prefix matching.

3. **Filtering by Year Range**:

• Use a range check with >= and <= for the year.