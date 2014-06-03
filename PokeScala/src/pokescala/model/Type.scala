package pokescala.model

import java.time.LocalDateTime

class Type(
    val name : String,
    val id : Int,
    val offensiveMults : Map[Int, Double],
    val defensiveMults : Map[Int, Double],
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Type] {
  
  val registry = TypeRegistry;

}

object TypeRegistry extends ModelRegistry[Type] {
  
}