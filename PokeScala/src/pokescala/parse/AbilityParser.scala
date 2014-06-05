package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.Try

object AbilityParser extends Parser[Ability] {
  
  def parse(implicit raw : Map[String, Any]) : Try[Ability] = Try {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val description = extract[String]("description");
    
    new Ability(name, description, id, resourceURI, created, modified);
  };

}