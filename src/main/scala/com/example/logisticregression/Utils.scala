package com.example.logisticregression

import ai.djl.ndarray.NDArray
import ai.djl.ndarray.NDManager

object Utils {
  def sigmoid(x: NDArray): NDArray = {
    x.exp().div(x.exp().add(1))
  }

  def binaryCrossEntropyLoss(labels: NDArray, predictions: NDArray): NDArray = {
    val one = labels.getManager.ones(labels.getShape)
    val loss = labels.mul(predictions.log()).add(labels.neg().add(one).mul(predictions.neg().add(one).log())).neg()
    loss.mean()
  }
}
