package com.example.logisticregression

import ai.djl.ndarray.{NDArray, NDList, NDManager}
import ai.djl.ndarray.types.{DataType, Shape}
import java.io._

class LogisticRegression(manager: NDManager, inputSize: Long) {
  private var weights: NDArray = manager.randomUniform(0, 0.01f, new Shape(inputSize))

  def train(features: NDArray, labels: NDArray, valFeatures: NDArray, valLabels: NDArray, epochs: Int, learningRate: Float, earlyStop: Boolean = true): Unit = {
    var bestValLoss = Float.MaxValue
    var bestWeights: NDArray = weights.duplicate()
    var patience = 5
    var patienceCounter = 0

    for (epoch <- 0 until epochs if patienceCounter < patience) {
      val predictions = Utils.sigmoid(features.dot(weights)).reshape(labels.getShape)
      val loss = Utils.binaryCrossEntropyLoss(labels, predictions)
      val lossValue = loss.getFloat() // Extract the scalar value from NDArray
      val gradients = computeGradients(features, labels)

      val trainAccuracy = calculateAccuracy(predict(features), labels)

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

  private def computeGradients(features: NDArray, labels: NDArray): NDArray = {
    val predictions = predictProbabilities(features)
    val errors = predictions.sub(labels)
    features.transpose().dot(errors).div(features.getShape.get(0))
  }

  def calculateAccuracy(predictions: NDArray, labels: NDArray): Float = {
    val correctPredictions = predictions.eq(labels).sum().toType(DataType.FLOAT32, false).getFloat()
    val totalSamples = labels.getShape.get(0).toFloat
    correctPredictions / totalSamples
  }

  def saveModel(filePath: String): Unit = {
    val oos = new ObjectOutputStream(new FileOutputStream(filePath))
    try {
      val data = weights.toArray
      oos.writeObject(data)
    } finally {
      oos.close()
    }
  }

  def loadModel(filePath: String): Unit = {
    val ois = new ObjectInputStream(new FileInputStream(filePath))
    try {
      val data = ois.readObject().asInstanceOf[Array[Number]]
      val floatData = data.map(_.floatValue())
      weights = manager.create(floatData, new Shape(floatData.length))
    } finally {
      ois.close()
    }
  }
}
