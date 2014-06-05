package pokescala.model

import java.time.LocalDateTime
import java.time.Year

class Game(
    val name : String,
    val generation : Int,
    val releaseYear : Int,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Game] {

  val registry = GameRegistry;
  registry.register(this);
  
  override def toString = s"$name; $generation; $releaseYear; " + super.toString;
}

object GameRegistry extends ModelRegistry[Game] {
  
}