# Chain Game

This project implements a chain game in Java, where players navigate through a grid, collect points, and create chains. The game keeps track of player scores and maintains a high score table.

## Project Overview

The project consists of several Java classes:
- `Main.java`: The main class that runs the game.
- `Game.java`: The class that manages the game logic, including initializing the game, handling input, and updating the game state.
- `Cursor.java`: A class that represents the player's cursor, including movement.
- `DLL.java`: A class that implements a doubly linked list for managing high scores.
- `MLL.java`: A class that implements a multi-level linked list for managing game tables.
- `NodeDLL.java`: A class representing a node in the doubly linked list.
- `NodeMLL.java`: A class representing a node in the multi-level linked list.
- `SLL.java`: A class that implements a singly linked list for managing selected coordinates and points.

### Features

The game includes:
1. Generating a grid with random points and obstacles.
2. Placing and navigating a player cursor.
3. Creating chains by selecting points on the grid.
4. Checking the validity of created chains.
5. Calculating and updating scores based on created chains.
6. Displaying a high score table.

## Requirements

The project requires Java Development Kit (JDK) to compile and run the Java files. Additionally, the Enigma Console library is used for console output.

## Usage

1. Clone the repository:

    ```bash
    git clone https://github.com/barissolcay/Chain-Game.git
    cd Chain-Game
    ```

2. Ensure you have the Enigma Console library in your classpath.

3. Compile the Java files:

    ```bash
    javac src/*.java
    ```

4. Run the game:

    ```bash
    java src/Main
    ```

## Game Flow

1. The game initializes a grid with random points and obstacles.
2. The player cursor is placed at a random position on the grid.
3. The player navigates through the grid using arrow keys, collecting points and creating chains.
4. The player can submit chains by pressing the Enter key.
5. The game checks the validity of created chains and updates the score.
6. The game ends when the player creates an invalid chain or chooses to exit.

## High Scores

The game maintains a high score table in a text file. The table is sorted in descending order of scores, and the top scores are displayed.

## Contributing

Feel free to open issues or submit pull requests if you have suggestions for improvements or find any bugs.

## License

MIT License

```markdown
MIT License

Copyright (c) 2025 Baris Solcay

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
