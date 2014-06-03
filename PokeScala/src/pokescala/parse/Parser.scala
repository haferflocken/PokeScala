package pokescala.parse

import scala.util.parsing.json.JSONObject

abstract class Parser[T] {

  def parse(raw : Map[String, Any]) : T;
  
}