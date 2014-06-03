package pokescala.model

import java.time.LocalDateTime

class Type(
    val name : String,
    val id : Int,
    val ineffective : Vector[Int],
    val noEffect : Vector[Int],
    val superEffective : Vector[Int],
    val resists : Vector[Int],
    val weakTo : Vector[Int],
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Type] {
  
  val registry = TypeRegistry;

}

object TypeRegistry extends ModelRegistry[Type] {
  
}