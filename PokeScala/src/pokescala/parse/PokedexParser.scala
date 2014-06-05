package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import java.time.LocalDateTime
import scala.util.Try

object PokedexParser extends Parser[Pokedex] {

  def parse(implicit raw : Map[String, Any]) : Try[Pokedex] = Try {
    val resourceURI = extract[String]("resource_uri");
    val created = LocalDateTime.parse(extract[String]("created"));
    val modified = LocalDateTime.parse(extract[String]("modified"));
    
    val name = extract[String]("name");
    val pokemon = extractResourceURIs(raw("pokemon"));
    
    new Pokedex(name, pokemon, resourceURI, created, modified);
  };
  
}