package com.example.logisticregression

import ai.djl.ndarray._
import org.scalatest.funsuite.AnyFunSuite

class LogisticRegressionTest extends AnyFunSuite {
  test("Logistic Regression Train and Predict") {
    val manager = NDManager.newBaseManager()
    val model = new LogisticRegression(manager, 13) // Assuming 13 features for testing
    val features = manager.ones(new Shape(10, 13)) // 10 samples, 13 features each
    val labels = manager.ones(new Shape(10)) // 10 labels
    val valFeatures = manager.ones(new Shape(5, 13)) // 5 validation samples
    val valLabels = manager.ones(new Shape(5)) // 5 validation labels
    
    model.train(features, labels, valFeatures, valLabels, epochs = 10, learningRate = 0.01f)
    
    val predictions = model.predict(features)
    assert(predictions != null)
  }
}
