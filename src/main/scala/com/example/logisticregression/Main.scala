package com.example.logisticregression

import ai.djl.ndarray.NDManager

object Main {
  def main(args: Array[String]): Unit = {
    // Create a new NDManager, which is responsible for managing the lifecycle of NDArrays
    val manager = NDManager.newBaseManager()
    
    // Create an instance of DataLoader to load the training data
    val dataLoader = new DataLoader(manager)
    val trainingFeatures = dataLoader.loadTrainingFeatures()
    val trainingLabels = dataLoader.loadTrainingLabels()

    // Initialize the logistic regression model
    val model = new LogisticRegression(manager)
    
    // Train the model with the training data
    model.train(trainingFeatures, trainingLabels, epochs = 1000, learningRate = 0.01f)

    // Test the model with a sample test feature
    val testFeatures = manager.create(Array(5.0f, 6.0f))
    val prediction = model.predict(testFeatures)
    println(s"Prediction: ${prediction.getFloat()}")
  }
}
