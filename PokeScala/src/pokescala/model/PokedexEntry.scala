package pokescala.model

import java.time.LocalDateTime

class PokedexEntry(
    val name : String,
    val description : String,
    val games : Vector[String],
    val pokemon : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[PokedexEntry] {
  
  val registry = PokedexEntryRegistry;

}

object PokedexEntryRegistry extends ModelRegistry[PokedexEntry] {
}