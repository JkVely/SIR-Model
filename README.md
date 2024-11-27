# SIR Model Simulation

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-3776AB?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Description

This project implements a simulation of the **SIR** (Susceptible, Infected, Recovered) epidemiological model using **Java** and **JavaFX** for the graphical interface. The main objective is to provide a visual and interactive tool to observe how different transmission and recovery rates affect the spread of a disease within a population.

The simulation allows adjusting key parameters such as grid size, transmission rate, and recovery rate, showing in real-time the number of susceptible, infected, and recovered individuals over time through dynamic charts.

<p align="center">
  <img alt="SIR Model Simulation" src="https://mattravenhall.github.io/assets/posts/SIRdiagram.png">
</p>

## Features

- **Intuitive Graphical Interface:** The application uses JavaFX to provide a user-friendly and visually appealing experience.
- **Dynamic Parameter Configuration:** Users can adjust the transmission rate, recovery rate, and grid size to see how these factors influence the simulation.
- **Real-Time Visualization:** A dynamic chart displays changes in the number of susceptible, infected, and recovered individuals as the simulation progresses.
- **Complete Simulation Control:** The application allows starting, stopping, and stepping through the simulation, providing detailed control over the process.

## Project Structure

```plaintext
src/
│
├── main/
│   ├── java/
│   │   └── dir/
│   │       ├── controller/   # Controllers to handle the logic of the interface and simulation
│   │       ├── model/        # Model defining the rules of the SIR model
│   │       └── view/         # FXML and CSS files for the graphical interface
│   └── resources/            # Resource files such as FXML and stylesheets
└── test/                     # Unit tests for the model and controllers
```

## Installation

- Clone the repository:
```bash
git clone https://github.com/JkVely/SIR-Model.git
cd SIR-Model
```
- Build the project:
```bash
mvn clean install
```
- Run the application:
```bash
mvn javafx:run
```

## Usage

- **Start the Simulation:** Click the "Start" button in the main menu.
- **Configure Parameters:** Adjust the sliders and text fields in the right panel to set the transmission rate, recovery rate, and grid size.
- **Control the Simulation:** Use the buttons to start, stop, and step through the simulation.

## Screenshots

### Application Main Menu
<img alt="Application Main Menu" src="https://i.imgur.com/Z6kPHY4.png">

### Simulation Panel (Initial State)
<img alt="Simulation Panel (Initial State)" src="https://i.imgur.com/XPh6Rsg.png">

### Simulation Panel (Grid Generated)
<img alt="Simulation Panel (Grid Generated)" src="https://i.imgur.com/05yVCLl.png">

### Simulation Panel (During Simulation)
<img alt="Simulation Panel (During Simulation)" src="https://i.imgur.com/y4zZdqH.png">

### Simulation Panel (Simulation Completed)
<img alt="Simulation Panel (Simulation Completed)" src="https://i.imgur.com/gmLMyGc.jpg">

## Contact

Juan Carlos Quintero - [@JkVely](https://github.com/JkVely) - jkquinteror@gmail.com

