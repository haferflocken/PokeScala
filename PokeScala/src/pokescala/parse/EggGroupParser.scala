package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object EggGroupParser extends Parser[EggGroup] {
  
  def parse(implicit raw : Map[String, Any]) : EggGroup = {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val pokemon = extractResourceURIs(raw("pokemon"));
    
    return new EggGroup(name, pokemon, id, resourceURI, created, modified);
  };

}