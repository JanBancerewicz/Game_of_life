# Game of Life - Implementation in Java

## Introduction

The Game of Life is a simple simulator game, played on a grid of square or hexagonal cells, where each cell can contain 1 organism. The state of the board evolves over discrete time steps according to the following rules:
- Plants can spread to a random neighbouring cell.
- Animals can reproduce when 2 of the same species collide.
- When 2 organisms collide, the stronger one consumes the weaker one.
- There is exacly 1 human on the map, controlled by player, so they can interact with the virtual world.

## Features

- **Graphical User Interface (GUI)**: Built using Java Swing, the application provides an intuitive interface to interact with the Game of Life.
- **Customizable Grid**: User can define the size of the grid.
- **Hexagonal Map**: User is prompted to either pick the hexagonal map or the default one.
- **Interactive Human Organism**: Allows the player to traverse the map, eat the other organisms.
- **Logger**: It helps to keep a track on the events that occurred during the turn.
- **Special Ability**: Can be used to destroy everything around human, continuously for five turns.
- 

## Installation

To run this application, ensure you have Java Development Kit (JDK) installed on your system.

1. **Clone the repository**:
```sh
    git clone https://github.com/JanBancerewicz/Game_of_life.git
```

2. **Compile the application**:

```sh
    javac -d bin src/com/Game_of_life/*.java
```

3. **Run the application**:

   ```sh
    java -cp bin com.Game_of_life.Main
   ```

## Usage
1. **Starting the Application**:

- Run the application using the command above.
- The main window will appear with an empty grid.
- 
2. **Setting Up the Grid**:

- Click on the grid cells to toggle between alive (black) and dead (white) states.
- Use the "Clear" button to reset the grid to all dead cells.
- 
3. **Simulation Control**:

- Click "Start" to begin the simulation.
- Click "Pause" to pause the simulation.
- Click "Reset" to stop the simulation and clear the grid.

## License
This project is licensed under the MIT License. See the [License](LICENSE) file for details.
