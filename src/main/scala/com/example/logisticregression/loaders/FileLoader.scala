package com.example.logisticregression.loaders

import ai.djl.ndarray.{NDArray, NDManager}
import org.apache.commons.csv.CSVParser
import java.io.FileReader
import java.util.Arrays
import scala.collection.JavaConverters._

object FileLoader {
  def loadDatasetFromFile(manager: NDManager, filePath: String): (NDArray, NDArray) = {
    val parser = CSVParser.parse(new FileReader(filePath), org.apache.commons.csv.CSVFormat.DEFAULT.withHeader())
    val featuresList = new java.util.ArrayList[Array[Float]]()
    val labelsList = new java.util.ArrayList[java.lang.Float]()

    parser.asScala.foreach { record =>
      val featureArray = record.toMap.values.toArray.map(_.toString.toFloat)
      val features = Arrays.copyOf(featureArray, featureArray.length - 1).map(_.toFloat)
      val label = featureArray.last.toFloat
      featuresList.add(features)
      labelsList.add(label)
    }

    val featuresArray = featuresList.toArray(new Array[Array[Float]](featuresList.size()))
    val labelsArray = labelsList.toArray(new Array[java.lang.Float](labelsList.size())).map(_.toFloat)

    val features = manager.create(featuresArray)
    val labels = manager.create(labelsArray)

    (features, labels)
  }
}
