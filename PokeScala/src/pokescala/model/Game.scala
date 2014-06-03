package pokescala.model

import java.time.LocalDateTime
import java.time.Year

class Game(
    val name : String,
    val generation : Int,
    val releaseYear : Year,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Game] {

  val registry = GameRegistry;
}

object GameRegistry extends ModelRegistry[Game] {
  
}