# Architecture Document

## High-Level Architecture
The project is structured following the standard Scala project conventions. It is organized into different components to separate concerns and improve maintainability.

## Directory Structure
logistic-regression-djl/
├── src/
│ ├── main/
│ │ ├── scala/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ └── logisticregression/
│ │ │ ├── Main.scala
│ │ │ ├── LogisticRegression.scala
│ │ │ ├── DataLoader.scala
│ │ │ └── Utils.scala
│ ├── test/
│ │ ├── scala/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ └── logisticregression/
│ │ │ ├── LogisticRegressionTest.scala
│ │ │ └── DataLoaderTest.scala
├── build.sbt
├── README.md
├── .gitignore
└── docs/
├── design.md
├── architecture.md
└── usage.md


## Components

### Main
The main application entry point that initializes data loading, training, and prediction.

### LogisticRegression
The core of the logistic regression implementation, responsible for model training and prediction.

### DataLoader
Manages data loading and preprocessing.

### Utils
Provides utility functions such as the binary cross-entropy loss calculation.

## Dependencies
The project uses the following dependencies managed by SBT:
- DJL (Deep Java Library) for machine learning operations.
- ScalaTest for unit testing.
