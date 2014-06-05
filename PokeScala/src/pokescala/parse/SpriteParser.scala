package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object SpriteParser extends Parser[Sprite] {
  
  def parse(implicit raw : Map[String, Any]) : Sprite = {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val pokemon = extract[String]("resource_uri")(extract[Map[String, Any]]("pokemon"));
    val image = extract[String]("image");
    
    return new Sprite(name, pokemon, image, id, resourceURI, created, modified);
  };

}