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

To run the project, execute the `Main` class:
```bash
sbt run
```

## Loading Datasets

### Boston Dataset
To load and process the Boston housing dataset, the `BostonLoader` class converts the median value of owner-occupied homes (medv) into a binary label.

### Breast Cancer Dataset
The `BreastCancerLoader` class processes the Wisconsin Diagnostic Breast Cancer dataset, distinguishing between malignant and benign cases.

### Iris Dataset
The `IrisLoader` class uses the Iris dataset, but only includes `Iris-setosa` and `Iris-versicolor` for binary classification.

### Wine Dataset
The `WineLoader` class processes the Wine dataset and considers only the first two classes for binary classification.
