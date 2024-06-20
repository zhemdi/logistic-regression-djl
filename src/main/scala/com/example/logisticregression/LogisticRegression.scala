package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDManager}
import ai.djl.ndarray.types.Shape

class LogisticRegression(manager: NDManager) {
  // Initialize the weights of the model
  private val weights: NDArray = manager.ones(new Shape(2, 1))

  // Train the model using gradient descent
  def train(features: NDArray, labels: NDArray, epochs: Int, learningRate: Float): Unit = {
    for (epoch <- 0 until epochs) {
      // Compute gradients for the weights
      val gradients = computeGradients(features, labels)
      
      // Update weights using the computed gradients and learning rate
      weights.subi(gradients.mul(learningRate))
      
      // Print loss every 100 epochs
      if (epoch % 100 == 0) {
        val loss = Utils.binaryCrossEntropyLoss(labels, features.dot(weights).sigmoid())
        println(s"Epoch: $epoch, Loss: ${loss.getFloat()}")
      }
    }
  }

  // Predict the class for given features
  def predict(features: NDArray): NDArray = {
    features.dot(weights).sigmoid().round()
  }

  // Compute gradients for the weights based on the features and labels
  private def computeGradients(features: NDArray, labels: NDArray): NDArray = {
    val predictions = features.dot(weights).sigmoid()
    val errors = predictions.sub(labels)
    features.transpose().dot(errors).div(features.getShape.get(0))
  }
}
