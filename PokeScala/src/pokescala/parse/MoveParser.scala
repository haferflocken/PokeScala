package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import scala.util.Try

object MoveParser extends Parser[Move] {

  def parse(implicit raw : Map[String, Any]) : Try[Move] = Try {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val description = extract[String]("description");
    val power = extract[Double]("power").toInt;
    val accuracy = extract[Double]("accuracy").toInt;
    val category = Move.Category(extract[String]("category"));
    val pp = extract[Double]("pp").toInt;
    
    new Move(name, description, power, accuracy, category, pp, id, resourceURI, created, modified);
  };
  
}