package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDManager}
import ai.djl.ndarray.types.Shape

class DataLoader(manager: NDManager) {
  // Load the training features
  def loadTrainingFeatures(): NDArray = {
    val features = Array(
      Array(1.0f, 2.0f),
      Array(2.0f, 3.0f),
      Array(3.0f, 4.0f),
      Array(4.0f, 5.0f)
    )
    manager.create(features)
  }

  // Load the training labels
  def loadTrainingLabels(): NDArray = {
    val labels = Array(0.0f, 0.0f, 1.0f, 1.0f)
    manager.create(labels).reshape(new Shape(4, 1))
  }
}
