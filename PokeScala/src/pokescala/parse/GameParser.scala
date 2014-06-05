package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import java.time.Year

object GameParser extends Parser[Game] {
  
  def parse(implicit raw : Map[String, Any]) : Game = {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val generation = extract[Double]("generation").toInt;
    val releaseYear = extract[Double]("release_year").toInt;
    
    return new Game(name, generation, releaseYear, id, resourceURI, created, modified);
  };

}