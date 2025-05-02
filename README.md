# Maze Solver Project

A Java-based command-line tool that generates and solves mazes using depth-first search (DFS) and backtracking techniques.

---

##  Features

- **Maze Generation:** Produces random mazes of specified dimensions (`MazeGenerator`).
- **Maze Solving:** Finds the shortest path using a depth-first search (DFS) algorithm with backtracking (`MazeSolverProject`).
- **Utility Classes:** Includes a stack implementation (`Stack`) and common maze operations (`MazeUtility`).
- **Step-by-Step Visualization:** Displays the solving process in the console, showing each step.

---

##  Project Structure

```
MazeSolverProject/
├── MazeGenerator.java    # Generates random maze layouts
├── MazeSolverProject.java# Main class: controls generation, solving, and UI
├── MazeUtility.java      # Provides boundary checks and neighbor calculations
└── Stack.java            # Simple generic stack used by DFS algorithm
```

---

##  Requirements

- **Java SE 8** or higher
- A terminal or command-line interface

---

##  Installation & Usage

1. **Clone or download the repository**
   ```sh
   git clone https://github.com/oktaykorkut/MazeSolverProject.git
   cd MazeSolverProject
   ```

2. **Compile the source files**
   ```sh
   javac *.java
   ```

3. **Run the program**
   ```sh
   java MazeSolverProject
   ```

4. **Optional parameters**
   - Provide width and height as command-line arguments:
     ```sh
     java MazeSolverProject 20 15
     ```
   - This example generates a 20-column by 15-row maze.

---

##  Class Summaries

### MazeGenerator
- **Parameters:** Width and height of the maze.
- **Algorithm:** Uses DFS to carve out passages by knocking down walls in a grid.
- **Output:** Returns a 2D integer array (`int[][]`) representing the generated maze.

### MazeSolverProject
- **Main Method:** Reads user input, invokes maze generation and solving.
- **Visualization:** Prints the maze and the solution path to the console.
- **Reporting:** Displays the time taken and number of steps to solve.

### MazeUtility
- **Bounds Checking:** Validates cell coordinates within maze limits and identifies walls.
- **Neighbor Retrieval:** Lists adjacent cells for DFS exploration.
- **Coordinate Conversion:** Maps between 2D coordinates and array indices.

### Stack<T>
- **Generic Implementation:** Provides a stack data structure for any type.
- **Methods:** Includes `push`, `pop`, `peek`, and `isEmpty`.
- **Usage:** Stores path history during DFS to enable backtracking.
