# Chain Game 🎮

A Java-based console game where players create chains of numbers in a grid-based environment. The game features dynamic scoring, high score tracking, and strategic gameplay elements.

## 🎯 Game Overview

Chain Game is an interactive console game where players navigate through a grid, connecting numbers to create sequential chains. The game combines strategy, planning, and quick thinking to achieve the highest possible score.

## ✨ Features

- Grid-based gameplay environment
- Interactive cursor movement
- Chain creation mechanics
- Score multiplier system
- High score tracking
- Color-coded interface
- Seed-based map generation
- Multi-linked list data structure implementation

## 🎮 Game Elements

### Map Components
- `+`, `-`, `|` : Border elements
- `1-4` : Collectible numbers (shown in red)
- `_` : Player cursor path (shown in cyan)
- `.` : Completed chain markers
- ` ` : Empty space

### Controls
- ↑↓←→ : Move cursor
- SPACE : Select/Deselect point
- ENTER : Confirm chain
- E : End game and save score

## 🔧 Technical Implementation

### Data Structures Used
1. **Single Linked List (SLL)**
   - Chain coordination tracking
   - Point sequence storage

2. **Double Linked List (DLL)**
   - High score management
   - Sorted score tracking

3. **Multi Linked List (MLL)**
   - Game table management
   - Chain relationships

4. **Node Classes**
   - `NodeSLL`: Single linked list nodes
   - `NodeDLL`: Double linked list nodes
   - `NodeMLL`: Multi linked list nodes

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher
- Enigma Console Library
- IDE (Eclipse/IntelliJ IDEA recommended)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/barissolcay/Chain-Game.git
