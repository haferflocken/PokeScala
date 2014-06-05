package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import java.time.LocalDateTime

object PokedexParser extends Parser[Pokedex] {

  def parse(implicit raw : Map[String, Any]) : Pokedex = {
    val resourceURI = extract[String]("resource_uri");
    val created = LocalDateTime.parse(extract[String]("created"));
    val modified = LocalDateTime.parse(extract[String]("modified"));
    
    val name = extract[String]("name");
    val pokemon = extractResourceURIs(raw("pokemon"));
    
    return new Pokedex(name, pokemon, resourceURI, created, modified);
  };
  
}