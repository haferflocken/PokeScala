package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object PokedexParser extends Parser[Pokedex] {

  def parse(implicit raw : Map[String, Any]) : Pokedex = {
    val (id, resourceURI, created, modified) = extractModelInfo(raw);
    val name = raw("name").asInstanceOf[String];
    val pokemon = extractResourceURIs(raw("pokemon"));
    
    return new Pokedex(name, pokemon, id, resourceURI, created, modified);
  };
  
}