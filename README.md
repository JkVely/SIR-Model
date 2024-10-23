# SIR Model Simulation

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-3776AB?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Descripción

Este proyecto implementa una simulación del modelo epidemiológico **SIR** (Susceptible, Infectado, Recuperado) utilizando **Java** y **JavaFX** para la interfaz gráfica. El objetivo principal es proporcionar una herramienta visual e interactiva para observar cómo diferentes tasas de transmisión y recuperación afectan la propagación de una enfermedad dentro de una población.

La simulación permite ajustar parámetros clave como el tamaño de la cuadrícula, la tasa de transmisión y la tasa de recuperación, mostrando en tiempo real el número de individuos susceptibles, infectados y recuperados a lo largo del tiempo mediante gráficos dinámicos.

<p align="center">
  <img alt="SIR Model Simulation" src="https://mattravenhall.github.io/assets/posts/SIRdiagram.png">
</p>

## Características

- **Interfaz Gráfica Intuitiva:** La aplicación utiliza JavaFX para ofrecer una experiencia de usuario amigable y visualmente atractiva.
- **Configuración Dinámica de Parámetros:** Los usuarios pueden ajustar la tasa de transmisión, la tasa de recuperación y el tamaño de la cuadrícula para ver cómo estos factores influyen en la simulación.
- **Visualización en Tiempo Real:** Un gráfico dinámico muestra los cambios en el número de individuos susceptibles, infectados y recuperados a medida que avanza la simulación.
- **Control Completo de la Simulación:** La aplicación permite iniciar, detener y avanzar la simulación paso a paso, brindando un control detallado sobre el proceso.

## Estructura del Proyecto

```plaintext
src/
│
├── main/
│   ├── java/
│   │   └── dir/
│   │       ├── controller/   # Controladores para manejar la lógica de la interfaz y la simulación
│   │       ├── model/        # Modelo que define las reglas del modelo SIR
│   │       └── view/         # Archivos FXML y CSS para la interfaz gráfica
│   └── resources/            # Archivos de recursos como FXML y hojas de estilo
└── test/                     # Pruebas unitarias del modelo y controladores
```
## Instalación
- Clonar el repositorio:
```bash
git clone https://github.com/JkVely/SIR-Model.git
cd SIR-Model-Simulation
```
- Compilar el proyecto:
```bash
mvn clean install
```
- Ejecutar la aplicación:
```bash
mvn javafx:run
```

## Uso
- Iniciar la Simulación: Haz clic en el botón "Start" en el menú principal.
- Configurar Parámetros: Ajusta los sliders y campos de texto en el panel derecho para configurar la tasa de transmisión, tasa de recuperación, y tamaño de la cuadrícula.
- Controlar la Simulación: Utiliza los botones para iniciar, detener y avanzar la simulación paso a paso.
## Capturas de Pantalla
Menú Principal
<img alt="Main Menu" src="https://mattravenhall.github.io/assets/posts/SIRdiagram.png">
Panel Principal
<img alt="Main Panel" src="https://mattravenhall.github.io/assets/posts/SIRdiagram.png">

Contacto
Juan Carlos Quintero - @JkVely - jkquinteror@gmail.com

