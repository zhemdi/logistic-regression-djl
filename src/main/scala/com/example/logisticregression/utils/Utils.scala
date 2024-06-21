package com.example.logisticregression

import ai.djl.ndarray.NDArray
import ai.djl.ndarray.NDManager

object Utils {
  def normalizeFeatures(features: NDArray): NDArray = {
    val mean = features.mean(Array(0))
    val std = features.sub(mean).pow(2).mean(Array(0)).sqrt()
    features.sub(mean).div(std)
  }

  def sigmoid(x: NDArray): NDArray = {
    x.exp().div(x.exp().add(1))
  }

  def binaryCrossEntropyLoss(labels: NDArray, predictions: NDArray): NDArray = {
    val one = labels.getManager.ones(labels.getShape)
    // println(s"Labels shape: ${labels.getShape}")
    // println(s"Predictions shape: ${predictions.getShape}")
    val loss = labels.mul(predictions.log()).add(labels.neg().add(one).mul(predictions.neg().add(one).log())).neg()
    loss.mean()
  }
}
