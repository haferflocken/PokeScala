package pokescala.model

import java.time.LocalDateTime

class EggGroup(
    val name : String,
    val pokemon : Vector[String],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[EggGroup] {
  
  val registry = EggGroupRegistry;
  registry.register(this);
  
  override def toString = s"$name; $pokemon; " + super.toString;

}

object EggGroupRegistry extends ModelRegistry[EggGroup] {
}