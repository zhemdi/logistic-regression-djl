package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDList, NDManager}
import ai.djl.ndarray.types.Shape

class LogisticRegression(manager: NDManager, inputSize: Long) {
  private var weights: NDArray = manager.randomUniform(0, 0.01f, new Shape(inputSize, 1))

  def train(features: NDArray, labels: NDArray, epochs: Int, learningRate: Float): Unit = {
    for (epoch <- 0 until epochs) {
      val predictions = Utils.sigmoid(features.dot(weights))
      val loss = Utils.binaryCrossEntropyLoss(labels, predictions)
      val lossValue = loss.getFloat() // Extract the scalar value from NDArray
      val gradients = computeGradients(features, labels)
      weights = weights.sub(gradients.mul(learningRate))
      println(s"Epoch $epoch: Loss = $lossValue")
    }
  }

  def predict(features: NDArray): NDArray = {
    val probabilities = predictProbabilities(features)
    println("Prediction probabilities: " + probabilities)
    probabilities.round()
  }


  def predictProbabilities(features: NDArray): NDArray = {
    Utils.sigmoid(features.dot(weights))
  }

  // Compute gradients for the weights based on the features and labels
  private def computeGradients(features: NDArray, labels: NDArray): NDArray = {
    val predictions = predictProbabilities(features)
    val errors = predictions.sub(labels)
    features.transpose().dot(errors).div(features.getShape.get(0))
  }
}
