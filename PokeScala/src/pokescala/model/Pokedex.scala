package pokescala.model

import java.time.LocalDateTime

class Pokedex(
    val name : String,
    val pokemon : Vector[String],
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Pokedex] {
  
  val id = 1;
  val registry = PokedexRegistry;
  registry.register(this);
  
  override def toString = s"$name; $pokemon; " + super.toString;

}

object PokedexRegistry extends ModelRegistry[Pokedex] {
}