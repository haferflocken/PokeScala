package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject

object AbilityParser extends Parser[Ability] {
  
  def parse(implicit raw : Map[String, Any]) : Ability = {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val description = extract[String]("description");
    
    return new Ability(name, description, id, resourceURI, created, modified);
  };

}