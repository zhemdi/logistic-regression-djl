package com.example.logisticregression

import ai.djl.ndarray.NDArray

object Utils {
  // Compute binary cross-entropy loss
  def binaryCrossEntropyLoss(yTrue: NDArray, yPred: NDArray): NDArray = {
    val epsilon = 1e-15f
    val yPredClipped = yPred.clip(epsilon, 1.0f - epsilon)
    yTrue.mul(yPredClipped.log()).add(yTrue.neg().add(1).mul(yPredClipped.neg().add(1).log())).mean().neg()
  }
}
