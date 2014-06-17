package pokescala.model

import java.time.LocalDateTime
import pokescala.net.PokeAPI

class Pokedex(
    val name : String,
    val pokemonURIs : Vector[String],
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Pokedex] {
  
  val id = 1;
  val registry = PokedexRegistry;
  registry.register(this);
  
  def loadAdjacent = for (uri <- pokemonURIs; p <- PokeAPI.pokemonByURI(uri)) yield p;
  
  override def toString = s"$name; $pokemonURIs; " + super.toString;

}

object PokedexRegistry extends ModelRegistry[Pokedex] {
}