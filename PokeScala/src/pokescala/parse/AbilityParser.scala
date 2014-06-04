package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject

object AbilityParser extends Parser[Ability] {
  
  def parse(implicit raw : Map[String, Any]) : Ability = {
    val (id, resourceURI, created, modified) = extractModelInfo(raw);
    val name = raw("name").asInstanceOf[String];
    val description = raw("description").asInstanceOf[String];
    
    return new Ability(name, description, id, resourceURI, created, modified);
  };

}