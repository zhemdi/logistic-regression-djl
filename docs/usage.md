# Usage Instructions

## Setup

1. Clone the repository:
```bash
git clone https://github.com/zhemdi/logistic-regression-djl.git
```
2. Navigate to the project directory:
```bash
cd logistic-regression-djl
```
3. Install the necessary dependencies using SBT:
```bash
sbt update
```

## Running the Project

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

To run the tests for the project, use the following command:
```bash
sbt test
```

This will execute all tests in the `test` directory and provide you with the results.

### Test Details

- `DataLoaderTest.scala`: Ensures that the data loading functionalities are working correctly.
- `LogisticRegressionTest.scala`: Verifies that the logistic regression model trains and predicts as expected.