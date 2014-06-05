package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import scala.util.Try

object EggGroupParser extends Parser[EggGroup] {
  
  def parse(implicit raw : Map[String, Any]) : Try[EggGroup] = Try {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val pokemon = extractResourceURIs(raw("pokemon"));
    
    new EggGroup(name, pokemon, id, resourceURI, created, modified);
  };

}