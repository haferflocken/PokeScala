package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject

object AbilityParser extends Parser[Ability] {
  
  def parse(raw : Map[String, Any]) : Ability = null; // TODO

}