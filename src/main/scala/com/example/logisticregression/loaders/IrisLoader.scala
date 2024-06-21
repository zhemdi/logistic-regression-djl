package com.example.logisticregression.loaders

import ai.djl.ndarray.{NDArray, NDManager}
import org.apache.commons.csv.CSVParser
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.Paths
import scala.collection.JavaConverters._

object IrisLoader {
  private val datasetDir = "datasets"

  def loadIrisDataset(manager: NDManager): (NDArray, NDArray) = {
    val file = downloadDataset("https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data", "iris.data")
    val parser = CSVParser.parse(file, java.nio.charset.StandardCharsets.UTF_8, org.apache.commons.csv.CSVFormat.DEFAULT)
    val featuresList = new java.util.ArrayList[Array[Float]]()
    val labelsList = new java.util.ArrayList[java.lang.Float]()

    parser.asScala.foreach { record =>
      val labelOption = record.get(4) match {
        case "Iris-setosa" => Some(0f)
        case "Iris-versicolor" => Some(1f)
        case _ => None // Skip "Iris-virginica" to keep it binary
      }
      labelOption.foreach { label =>
        val features = Array(
          record.get(0).toFloat,
          record.get(1).toFloat,
          record.get(2).toFloat,
          record.get(3).toFloat
        )
        featuresList.add(features)
        labelsList.add(label)
      }
    }

    val featuresArray = featuresList.toArray(new Array[Array[Float]](featuresList.size()))
    val labelsArray = labelsList.toArray(new Array[java.lang.Float](labelsList.size())).map(_.toFloat)

    val features = manager.create(featuresArray)
    val labels = manager.create(labelsArray)

    (features, labels)
  }

  private def downloadDataset(url: String, fileName: String): File = {
    val filePath = Paths.get(datasetDir, fileName).toString
    val file = new File(filePath)
    if (!file.exists()) {
      println(s"Downloading $fileName...")
      FileUtils.copyURLToFile(new URL(url), file)
    }
    file
  }
}
