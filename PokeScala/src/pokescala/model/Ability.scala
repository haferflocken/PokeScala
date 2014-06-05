package pokescala.model

import java.time.LocalDateTime

class Ability(
    val name : String,
    val description : String,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Ability] {
  
  val registry = AbilityRegistry;
  registry.register(this);
  
  override def toString = s"$name; $description; " + super.toString;

}

object AbilityRegistry extends ModelRegistry[Ability] {
  
}