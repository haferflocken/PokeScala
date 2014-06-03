package pokescala.model

import java.time.LocalDateTime

class EggGroup(
    val name : String,
    val pokemon : Vector[Int],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[EggGroup] {
  
  val registry = EggGroupRegistry;

}

object EggGroupRegistry extends ModelRegistry[EggGroup] {
}