package pokescala.model

import java.time.LocalDateTime
import scala.collection.mutable
import pokescala.net.PokeAPI

class PokedexEntry(
    val name : String,
    val description : String,
    val gameURIs : Vector[String],
    val pokemonURI : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[PokedexEntry] {
  
  val registry = PokedexEntryRegistry;
  registry.register(this);
  
  def loadAdjacent : Vector[Model[_]] = {
    val buff = new mutable.ArrayBuffer[Model[_]];
    for (uri <- gameURIs; game <- PokeAPI.gameByURI(uri)) 
      buff += game;
    for (pokemon <- PokeAPI.pokemonByURI(pokemonURI))
      buff += pokemon;
    return buff.toVector;
  }
  
  override def toString = s"$name; $description; $gameURIs; $pokemonURI; " + super.toString;

}

object PokedexEntryRegistry extends ModelRegistry[PokedexEntry] {
}