package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object PokedexEntryParser extends Parser[PokedexEntry] {

  def parse(implicit raw : Map[String, Any]) : PokedexEntry = {
    val (id, resourceURI, created, modified) = extractModelInfo(raw);
    val name = raw("name").asInstanceOf[String];
    val description = raw("description").asInstanceOf[String];
    val games = extractResourceURIs(raw("games"));
    val pokemon = raw("pokemon").asInstanceOf[Map[String, Any]]("resource_uri").asInstanceOf[String];
    
    return new PokedexEntry(name, description, games, pokemon, id, resourceURI, created, modified);
  };
  
}