# Architecture Document

## High-Level Architecture
The project is structured following the standard Scala project conventions. It is organized into different components to separate concerns and improve maintainability.

## Directory Structure
```bash
logistic-regression-djl/
├── docs/
│   ├── architecture.md
│   ├── design.md
│   └── usage.md
├── src/
│   ├── main/
│   │   ├── scala/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── logisticregression/
│   │   │               ├── loaders/
│   │   │               │   ├── BostonLoader.scala
│   │   │               │   ├── BreastCancerLoader.scala
│   │   │               │   ├── FileLoader.scala
│   │   │               │   ├── IrisLoader.scala
│   │   │               │   └── WineLoader.scala
│   │   │               ├── utils/
│   │   │               │   ├── DataLoader.scala
│   │   │               │   ├── LogisticRegression.scala
│   │   │               │   └── Main.scala
│   ├── test/
│   │   ├── scala/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── logisticregression/
│   │   │               ├── DataLoaderTest.scala
│   │   │               └── LogisticRegressionTest.scala
├── config/
│   ├── boston_config.json
│   ├── breast_cancer_config.json
│   ├── iris_config.json
│   ├── wine_config.json
├── README.md
└── build.sbt
```

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



