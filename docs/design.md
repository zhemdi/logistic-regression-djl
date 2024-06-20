# Design Document

## Overview
This project implements a simple binary logistic regression using the Deep Java Library (DJL) in Scala. The project demonstrates how to build, train, and evaluate a logistic regression model from scratch.

## Components

### Main
The entry point of the application, responsible for orchestrating the data loading, model training, and prediction processes.

### LogisticRegression
Implements the logistic regression model, including methods for training and prediction. It also includes the computation of gradients and updating the weights.

### DataLoader
Handles data loading and preprocessing. This includes loading training features and labels.

### Utils
Contains utility functions used across the project, such as the binary cross-entropy loss calculation.
