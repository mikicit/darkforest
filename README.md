# DarkForest

## Table of Contents

- [Description](#description)
- [Technical Requirements](#technical-requirements)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Architecture and Technologies](#architecture-and-technologies)
- [Features](#features)
- [Authors](#authors)
- [License](#license)

## Description

DarkForest is a straightforward game engine for 2D top-down games. The engine allows for game configuration without altering the source code. It supports location configuration, adding new elements to the game, player configuration, and more.

This game engine was developed in Java using the JavaFX library as a semester project for the [Programming in Java](https://intranet.fel.cvut.cz/en/education/bk/predmety/50/10/p5010706.html) course at [CTU in Prague](https://www.cvut.cz/).

Since it was created as a semester project, DarkForest is relatively simple in design. The primary goal was to study Java and understand the principles of software development in the context of Java and OOP. This project provided hands-on experience with Java programming and offered insights into the key concepts involved in building such software.

Documentation for the project can be found [here](https://mikicit.github.io/darkforest/).

## Technical Requirements

Для сборки и запуска проекта вам необходима JDK 21 и maven 3.9.8.  

## Installation and Setup

Чтобы запустить проект, выполните следующие шаги:

1. Clone the repository to your computer.
2. Navigate to the project folder.
3. Запустите приложение с помощью плагина javafx
```shell
mvn javafx:run
```

Вы также можете собрать проект в jar архив, шаги 1 и 2 остаются прежними, а затем выполните следующую команду:
```sh
mvn clean package
```

После этого в директории target появится jar архив, который можно запустить с помощью команды:
```sh
java -jar darkforest-1.0-SNAPSHOT.jar
```

Для сборки jar архива со всеми зависимостями используется плагин maven-shade-plugin. Следовательно, вы можете перемещать jar архив в любое место на вашем компьютере и запускать его без необходимости установки дополнительных библиотек. Главное не забудьте поместить вместе с jar архивом директорию config с конфигурационными файлами.

## Usage

### Main Menu

Once the user launches the application, the main menu is displayed.

![image](https://user-images.githubusercontent.com/30042943/178839527-e3eb28e6-36f2-47a0-ba43-5e2a5c6ad6e0.png)

### Game World

Next, the user presses the "Start Game" button to begin the game. The player will find himself in an open world. The player can navigate the map using the **W**, **A**, **S**, **D** keys.

![image](https://user-images.githubusercontent.com/30042943/178840949-e41a7db9-089f-410d-95a7-fe5a5c28336a.png)

### Combat system

Using the "**J**" key, the player can attack the enemy. Each monster has its own speed, visibility and attack radius. If a monster notices you, it will move after you until it dies, until you kill it or change location (through a portal).

![image](https://user-images.githubusercontent.com/30042943/178839964-4586c665-929f-4f9a-b706-54bf11d18c83.png)

### Game Menu

During the game the player can open the game menu by pressing a key **Escape**. During this time the game will be paused. In the game menu the player can exit to the main menu, save the game, load the game or exit the game.

![image](https://user-images.githubusercontent.com/30042943/178841445-ccfc4c17-7c9c-46c1-984a-56e0c09b6c9b.png)

### Inventory

The player can collect various items on the map, which he can then see in his inventory, which he can go to by pressing **I**. There will only be three types of items in the game (**weapons**, **armor** and **healing potions**). The player will be able to equip weapons and armor and use potions.

![image](https://user-images.githubusercontent.com/30042943/178840251-7eb2c81a-1539-4bc5-b78e-1d6297944a67.png)

The player can also select items in the inventory using the **W**, **A**, **S**, **D** keys, put on or take off an item using the **J** key, use an item using the **K** key, and delete an item using the **L** key. The currently selected item and items that are already equipped will stand out with different colors. To return back to the game, the player can use the "Escape" key.

### Portals

There are portals in the game that allow you to move between locations.

![image](https://user-images.githubusercontent.com/30042943/178841855-1531919b-43cd-49ee-a9ea-18f4449cd6d5.png)

### End game

If the player dies, the game will start again.

![image](https://user-images.githubusercontent.com/30042943/178840701-35adb011-6bca-4081-8498-1b8e45bdfed4.png)

## Architecture and Technologies

I tried my best to separate the main parts of the game to make it easier to expand.

The JavaFX library is used for graphics. The game world is rendered using canvas.

Almost everything in the game can be set up with configuration files, without the need to change the game code.

### Configuration

Все конфигурационные файлы расположены в директории config.

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


