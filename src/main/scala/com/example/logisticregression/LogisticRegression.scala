package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDList, NDManager}
import ai.djl.ndarray.types.{DataType, Shape}

class LogisticRegression(manager: NDManager, inputSize: Long) {
  private var weights: NDArray = manager.randomUniform(0, 0.01f, new Shape(inputSize))

  def train(features: NDArray, labels: NDArray, valFeatures: NDArray, valLabels: NDArray, epochs: Int, learningRate: Float, earlyStop: Boolean = true): Unit = {
    var bestValLoss = Float.MaxValue
    var bestWeights: NDArray = weights.duplicate()
    var patience = 5
    var patienceCounter = 0

    for (epoch <- 0 until epochs if patienceCounter < patience) {
      // print shapes of features, labels, and weights
      // println(s"Shapes: ${features.getShape}, ${labels.getShape}, ${weights.getShape}")
      // println(weights)
      val predictions = Utils.sigmoid(features.dot(weights)).reshape(labels.getShape)
      val loss = Utils.binaryCrossEntropyLoss(labels, predictions)
      val lossValue = loss.getFloat() // Extract the scalar value from NDArray
      val gradients = computeGradients(features, labels)
      // println(gradients)
      // print 10 first predictions and labels
      // println(predictions.get(":10"))
      // println(labels.get(":10"))
      
      // Calculate accuracy on training set
      val trainAccuracy = calculateAccuracy(predict(features), labels)
      
      // Calculate accuracy and loss on validation set
      val valPredictions = predictProbabilities(valFeatures).reshape(valLabels.getShape)
      val valLoss = Utils.binaryCrossEntropyLoss(valLabels, valPredictions)
      val valLossValue = valLoss.getFloat()
      val valAccuracy = calculateAccuracy(valPredictions.round(), valLabels)
      
      println(s"Epoch $epoch: Loss = $lossValue, Train Accuracy = $trainAccuracy, Val Loss = $valLossValue, Val Accuracy = $valAccuracy")
      
      if (earlyStop) {
        if (valLossValue < bestValLoss) {
          bestValLoss = valLossValue
          bestWeights = weights.duplicate()
          patienceCounter = 0
        } else {
          patienceCounter += 1
        }
      }
      
      weights = weights.sub(gradients.mul(learningRate))
    }

    if (earlyStop) {
      weights = bestWeights
    }
  }

  def predict(features: NDArray): NDArray = {
    val probabilities = predictProbabilities(features)
    probabilities.round()
  }

  def predictProbabilities(features: NDArray): NDArray = {
    Utils.sigmoid(features.dot(weights)).reshape(features.getShape.get(0))
  }

  // Compute gradients for the weights based on the features and labels
  private def computeGradients(features: NDArray, labels: NDArray): NDArray = {
    val predictions = predictProbabilities(features)
    val errors = predictions.sub(labels)
    features.transpose().dot(errors).div(features.getShape.get(0))
  }

  // Calculate accuracy based on predictions and true labels
  def calculateAccuracy(predictions: NDArray, labels: NDArray): Float = {
    val correctPredictions = predictions.eq(labels).sum().toType(DataType.FLOAT32, false).getFloat()
    val totalSamples = labels.getShape.get(0).toFloat
    correctPredictions / totalSamples
  }
}
