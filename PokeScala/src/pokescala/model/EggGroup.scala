package pokescala.model

import java.time.LocalDateTime
import pokescala.net.PokeAPI

class EggGroup(
    val name : String,
    val pokemonURIs : Vector[String],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[EggGroup] {
  
  val registry = EggGroupRegistry;
  registry.register(this);
  
  def loadAdjacent = for (uri <- pokemonURIs; p <- PokeAPI.pokemonByURI(uri)) yield p;
  
  override def toString = s"$name; $pokemonURIs; " + super.toString;

}

object EggGroupRegistry extends ModelRegistry[EggGroup] {
}