package com.example.logisticregression

import org.scalatest.flatspec.AnyFlatSpec
import ai.djl.ndarray.NDManager

class LogisticRegressionTest extends AnyFlatSpec {

  "LogisticRegression" should "train correctly and make predictions" in {
    val manager = NDManager.newBaseManager()
    val dataLoader = new DataLoader(manager)
    val trainingFeatures = dataLoader.loadTrainingFeatures()
    val trainingLabels = dataLoader.loadTrainingLabels()

    val model = new LogisticRegression(manager)
    model.train(trainingFeatures, trainingLabels, epochs = 1000, learningRate = 0.01f)

    val testFeatures = manager.create(Array(5.0f, 6.0f))
    val prediction = model.predict(testFeatures)
    assert(prediction.getFloat() == 1.0f)
  }
}
