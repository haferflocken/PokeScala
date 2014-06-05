package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object PokedexEntryParser extends Parser[PokedexEntry] {

  def parse(implicit raw : Map[String, Any]) : PokedexEntry = {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    val description = extract[String]("description");
    val games = extractResourceURIs(raw("games"));
    val pokemon = extract[String]("resource_uri")(extract[Map[String, Any]]("pokemon"))
    
    return new PokedexEntry(name, description, games, pokemon, id, resourceURI, created, modified);
  };
  
}