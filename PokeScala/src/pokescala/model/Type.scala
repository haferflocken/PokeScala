package pokescala.model

import java.time.LocalDateTime

class Type(
    val name : String,
    val offensiveMults : Map[String, Double],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Type] {
  
  val registry = TypeRegistry;
  registry.register(this);
  
  override def toString = s"$name; $offensiveMults; " + super.toString;

}

object TypeRegistry extends ModelRegistry[Type] {
  
}