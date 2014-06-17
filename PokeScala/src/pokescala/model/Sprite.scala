package pokescala.model

import java.time.LocalDateTime
import pokescala.net.PokeAPI

class Sprite(
    val name : String,
    val pokemonURI : String,
    val imageURI : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Sprite] {
  
  val registry = SpriteRegistry;
  registry.register(this);
  
  def loadAdjacent = Vector(PokeAPI.pokemonByURI(pokemonURI).getOrElse(null));
  
  override def toString = s"$name; $pokemonURI; $imageURI; " + super.toString;

}

object SpriteRegistry extends ModelRegistry[Sprite] {
  
}