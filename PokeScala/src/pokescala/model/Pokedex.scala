package pokescala.model

import java.time.LocalDateTime

class Pokedex(
    val name : String,
    val pokemon : Vector[Int],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Pokedex] {
  
  val registry = PokedexRegistry;

}

object PokedexRegistry extends ModelRegistry[Pokedex] {
}