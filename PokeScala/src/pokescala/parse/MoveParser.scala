package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object MoveParser extends Parser[Move] {

  def parse(implicit raw : Map[String, Any]) : Move = {
    val (id, resourceURI, created, modified) = extractModelInfo(raw);
    val name = raw("name").asInstanceOf[String];
    val description = raw("description").asInstanceOf[String];
    val power = raw("power").asInstanceOf[Int];
    val accuracy = raw("accuracy").asInstanceOf[Int];
    val category = Move.Category(raw("category").asInstanceOf[String]);
    val pp = raw("pp").asInstanceOf[Int];
    
    return new Move(name, description, power, accuracy, category, pp, id, resourceURI, created, modified);
  };
  
}