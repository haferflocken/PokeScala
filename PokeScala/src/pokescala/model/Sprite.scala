package pokescala.model

import java.time.LocalDateTime

class Sprite(
    val name : String,
    val pokemon : String,
    val image : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Sprite] {
  
  val registry = SpriteRegistry;

}

object SpriteRegistry extends ModelRegistry[Sprite] {
  
}