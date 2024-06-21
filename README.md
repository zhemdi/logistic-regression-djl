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
│   │   │               ├── loaders/
│   │   │               │   ├── BostonLoader.scala
│   │   │               │   ├── BreastCancerLoader.scala
│   │   │               │   ├── FileLoader.scala
│   │   │               │   ├── IrisLoader.scala
│   │   │               │   ├── WineLoader.scala
│   │   │               ├── utils/
│   │   │               │   ├── ConfigLoader.scala
│   │   │               │   ├── DataLoader.scala
│   │   │               │   ├── LogisticRegression.scala
│   │   │               │   ├── Main.scala
│   ├── test/
│   │   ├── scala/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── logisticregression/
│   │   │               ├── DataLoaderTest.scala
│   │   │               ├── LogisticRegressionTest.scala
├── config/
│   ├── boston_config.json
│   ├── breast_cancer_config.json
│   ├── iris_config.json
│   ├── wine_config.json
├── docs/
│   ├── design.md
│   ├── architecture.md
│   ├── usage.md
├── build.sbt
├── README.md
```

## Setup

1. Clone the repository.
2. Install the necessary dependencies using SBT.
3. Run the project.

## Usage

### Running the Project

To run the project, execute the `Main` class with a specific config file:

```bash
sbt "run <config_file>"
```

For example, to run the project with the Iris dataset configuration:

```bash
sbt "run config/iris_config.json"
```

### Specifying a Custom Dataset

If you want to use a custom dataset, you can specify the `datasetPath` in your config file:

```json
{
  "datasetName": "custom",
  "datasetPath": "path/to/your/dataset.csv",
  ...
}
```

The dataset should be a CSV file with the features in the columns and the label in the last column.

### Available Config Files

- `config/boston_config.json`: Configuration for the Boston Housing dataset.
- `config/breast_cancer_config.json`: Configuration for the Breast Cancer dataset.
- `config/iris_config.json`: Configuration for the Iris dataset.
- `config/wine_config.json`: Configuration for the Wine dataset.

## Running Tests

To ensure everything is working correctly, you can run the tests included in this project. Use the following command to run all tests:

```bash
sbt test
```

### Test Files
- `DataLoaderTest.scala`: Tests for the data loading functionality.
- `LogisticRegressionTest.scala`: Tests for the logistic regression model training and prediction.

## Loading Datasets

### Boston Dataset
The `BostonLoader` class in the `loaders` package downloads and processes the Boston housing dataset, converting the median value of owner-occupied homes (medv) into a binary label for logistic regression.

- Label `0`: If `medv` is lower than the median.
- Label `1`: If `medv` is higher than or equal to the median.

### Iris Dataset
The `IrisLoader` class in the `loaders` package downloads and processes the Iris dataset, converting the classes into a binary classification problem.

- Label `0`: Iris-setosa
- Label `1`: Iris-versicolor
- The Iris-virginica class is ignored to maintain a binary classification problem.

### Breast Cancer Dataset
The `BreastCancerLoader` class in the `loaders` package downloads and processes the Breast Cancer Wisconsin dataset, converting the diagnosis into a binary label.

- Label `0`: Benign
- Label `1`: Malignant

### Wine Dataset
The `WineLoader` class in the `loaders` package downloads and processes the Wine dataset, converting the classes into a binary classification problem.

- Label `0`: Class 1
- Label `1`: Class 2
- The Class 3 is ignored to maintain a binary classification problem.
