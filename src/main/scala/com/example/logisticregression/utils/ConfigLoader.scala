package com.example.logisticregression.utils

import java.io.FileReader
import org.json4s._
import org.json4s.native.JsonMethods._

object ConfigLoader {
  def loadConfig(configFilePath: String): Map[String, String] = {
    val reader = new FileReader(configFilePath)
    implicit val formats = DefaultFormats
    val json = parse(reader)
    json.extract[Map[String, String]]
  }
}
