# Logistic Regression with DJL

This project implements a simple binary logistic regression using the Deep Java Library (DJL) in Scala.

## Project Structure
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
├── README.md
└── build.sbt
```


## Setup

1. Clone the repository.
2. Install the necessary dependencies using SBT.
3. Run the project.

## Usage

Run the `Main` class to train the logistic regression model and make predictions.

## Loading Datasets

### Boston Dataset

The `BostonLoader` class in the `loaders` package downloads and processes the Boston housing dataset, converting the median value of owner-occupied homes (medv) into a binary label for logistic regression.

- Label `0`: If `medv` is lower than the mean.
- Label `1`: If `medv` is equal to or greater than the mean.

### Breast Cancer Dataset

The `BreastCancerLoader` class downloads and processes the Wisconsin Diagnostic Breast Cancer dataset. The dataset is used for binary classification to determine if the cancer is malignant or benign.

- Label `0`: Benign (B)
- Label `1`: Malignant (M)

### Iris Dataset

The `IrisLoader` class downloads and processes the Iris dataset. For binary classification, the dataset only includes `Iris-setosa` and `Iris-versicolor`, excluding `Iris-virginica`.

- Label `0`: Iris-setosa
- Label `1`: Iris-versicolor

### Wine Dataset

The `WineLoader` class downloads and processes the Wine dataset. For binary classification, the dataset includes only the first two classes, converting the class labels from {1, 2} to {0, 1}.

- Label `0`: First class
- Label `1`: Second class

## Dependencies

The project uses the following dependencies managed by SBT:
- DJL (Deep Java Library) for machine learning operations.
- ScalaTest for unit testing.
