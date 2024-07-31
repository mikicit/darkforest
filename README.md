# DarkForest

## Table of Contents

- [Description](#description)
- [Technical Requirements](#technical-requirements)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Architecture and Technologies](#architecture-and-technologies)
- [Features](#features)
- [Testing](#testing)
- [Authors](#authors)
- [License](#license)

## Description

DarkForest is a straightforward game engine for 2D top-down games. The engine allows for game configuration without altering the source code. It supports location configuration, adding new elements to the game, player configuration, and more.

This game engine was developed in Java using the JavaFX library as a semester project for the [Programming in Java](https://intranet.fel.cvut.cz/en/education/bk/predmety/50/10/p5010706.html) course at [CTU in Prague](https://www.cvut.cz/).

Since it was created as a semester project, DarkForest is relatively simple in design. The primary goal was to study Java and understand the principles of software development in the context of Java and OOP. This project provided hands-on experience with Java programming and offered insights into the key concepts involved in building such software.

Documentation for the project can be found [here](https://mikicit.github.io/darkforest/).

## Technical Requirements

To build and run the project, you need JDK 21 and Maven 3.9.8.

## Installation and Setup

To run the project, follow these steps:

1. Clone the repository to your computer.
2. Navigate to the project folder.
3. Run the application using the JavaFX plugin:
    ```sh
    mvn javafx:run
    ```

You can also build the project into a jar file. Steps 1 and 2 remain the same, then execute the following command:
```sh
mvn clean package
```

After that, a jar file will appear in the target directory, which can be run with the following command:
```sh
java -jar darkforest-1.0-SNAPSHOT.jar
```

The maven-shade-plugin is used to build the jar file with all dependencies. Therefore, you can move the jar file anywhere on your computer and run it without needing to install additional libraries. Just make sure to include the config directory with the configuration files alongside the jar file.

## Usage

### Main Menu

Once the user launches the application, the main menu is displayed.

![Main Menu](https://github.com/mikicit/darkforest/raw/images/main_menu.png)

### Game World

Next, the user presses the "Start Game" button to begin the game. The player will find himself in an open world. The player can navigate the map using the **W**, **A**, **S**, **D** keys.

![Game World](https://github.com/mikicit/darkforest/raw/images/game_world.png)

### Combat System

Using the "**J**" key, the player can attack the enemy. Each monster has its own speed, visibility and attack radius. If a monster notices you, it will move after you until it dies, until you kill it or change location (through a portal).

![Combat System](https://github.com/mikicit/darkforest/raw/images/combat_system.png)

### Game Menu

During the game the player can open the game menu by pressing a key **Escape**. During this time the game will be paused. In the game menu the player can exit to the main menu, save the game, load the game or exit the game.

![Game Menu](https://github.com/mikicit/darkforest/raw/images/game_menu.png)

### Inventory

The player can collect various items on the map, which he can then see in his inventory, which he can go to by pressing **I**. There will only be three types of items in the game (**weapons**, **armor** and **healing potions**). The player will be able to equip weapons and armor and use potions.

![Inventory](https://github.com/mikicit/darkforest/raw/images/inventory.png)

The player can also select items in the inventory using the **W**, **A**, **S**, **D** keys, put on or take off an item using the **J** key, use an item using the **K** key, and delete an item using the **L** key. The currently selected item and items that are already equipped will stand out with different colors. To return back to the game, the player can use the "Escape" key.

### Portals

There are portals in the game that allow you to move between locations.

![Portals](https://github.com/mikicit/darkforest/raw/images/portals.png)

### End Game

If the player dies, the game will start again.

![End Game](https://github.com/mikicit/darkforest/raw/images/game_over.png)

## Architecture and Technologies

I tried my best to separate the main parts of the game to make it easier to expand.

The JavaFX library is used for graphics. The game world is rendered using canvas.

Almost everything in the game can be set up with configuration files, without the need to change the game code.

### Configuration

All configuration files are located in the config directory. The game uses JSON and txt files to configure the game elements.

#### Player

Example player configuration.

```
{
  "name": "Mikita",
  "initialHealth": 500,
  "health": 500,
  "damage": 50,
  "armor": 0,
  "damageRadius": 10,
  "locationId": 1,
  "positionX": 17,
  "positionY": 9,
  "equippedWeaponId": 4,
  "equippedArmorId": -1,
  "inventory": [1, 5]
}
```

#### Map

Example map configuration. Each character represents a separate tile.

```
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBB
AAAABBBBBBBBBBBBBAAAAAAAAAAAAAAAAAAABBBBBBB
AAAABBBBBBBBBBBBBAAAAAAAAAAAAAABBBBBBBBBBBB
AAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBAAABBBBBBB
AAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBAAABBBBBBB
AAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBB
AAAAAAAAAAAAAABAAAABAAAAAAABBBBBBAAAAAAAAAA
AAAAAAAAABAAAAAAABAAAABAAAAAAAAAAAAAAAAAAAA
AAABAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAAAAA
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAABBBBBAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAABBBBBAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAABBBBBAAAAAAAAAAAAAAAAAAAA
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
```

#### Tile

```
{
  "id": "A",
  "passable": true
}
```

#### Location

```
{
  "id": 1,
  "name": "Dark forest",
  "portals": [
    {
      "id": 1,
      "positionX": 27,
      "positionY": 7
    }
  ],
  "items": [
    {
      "id": 1,
      "positionX": 36,
      "positionY": 9
    },
    {
      "id": 2,
      "positionX": 35,
      "positionY": 13
    },
    {
      "id": 3,
      "positionX": 11,
      "positionY": 9
    },
    {
      "id": 4,
      "positionX": 10,
      "positionY": 3
    },
    {
      "id": 7,
      "positionX": 13,
      "positionY": 11
    }
  ],
  "monsters": [
    {
      "id": 1,
      "positionX": 36,
      "positionY": 9
    },
    {
      "id": 2,
      "positionX": 35,
      "positionY": 11
    },
    {
      "id": 1,
      "positionX": 10,
      "positionY": 3
    },
    {
      "id": 3,
      "positionX": 7,
      "positionY": 12
    }
  ]
}
```

#### Portal

```
{
  "id": 1,
  "locationId": 2,
  "playerX": 13,
  "playerY": 5
}
```

#### Item

```
{
  "id": 6,
  "name": "Sphere of death",
  "type": "weapon",
  "damage": 100,
  "radius": 50
}
```

#### Enemy

```
{
  "id": 1,
  "name": "Life Eater",
  "health": 350,
  "damage": 20,
  "damageRadius": 20,
  "viewingRadius": 64,
  "speed": 70,
  "attackSpeed": 1000
}
```

## Features

- **Save and Load**: Players can save their progress and load from saved games.
- **Inventory System**: Players can manage their items using an inventory system.
- **Combat System**: Basic combat mechanics are implemented for player and monster interactions.
- **Element Configuration**: Easily configure various game elements including characters, locations, items, and portals without altering the source code.
- **Add New Elements**: Ability to add new locations, items, portals, and other game elements.
- **Behavior Algorithms for Monsters**: Monsters are controlled by simple behavior algorithms.

## Testing

In the context of the semester project, unit tests were written for some components of the game engine. The primary goal of these tests is to familiarize with the testing process rather than achieving comprehensive code coverage. The tests are located in the `src/test/java` directory.

## Authors

- [Mikita Citarovič](https://github.com/mikicit)

## License

MIT License

Copyright (c) 2024 Mikita A Citarovič

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


