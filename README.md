# Logistic Regression with DJL

This project implements a simple binary logistic regression using the Deep Java Library (DJL) in Scala.

## Project Structure
```bash
logistic-regression-djl/
├── src/
│   ├── main/
│   │   ├── scala/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── logisticregression/
│   │   │               ├── Main.scala
│   │   │               ├── LogisticRegression.scala
│   │   │               ├── DataLoader.scala
│   │   │               └── Utils.scala
│   ├── test/
│   │   ├── scala/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── logisticregression/
│   │   │               ├── LogisticRegressionTest.scala
│   │   │               └── DataLoaderTest.scala
├── build.sbt
├── README.md
├── .gitignore
└── docs/
    ├── design.md
    ├── architecture.md
    └── usage.md
```


## Setup

1. Clone the repository.
2. Install the necessary dependencies using SBT.
3. Run the project.

## Usage

Run the `Main` class to train the logistic regression model and make predictions.
