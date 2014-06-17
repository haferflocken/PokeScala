package pokescala.model

import java.time.LocalDateTime
import pokescala.net.PokeAPI

class Type(
    val name : String,
    val offensiveMults : Map[String, Double],
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Type] {
  
  val registry = TypeRegistry;
  registry.register(this);
  
  def loadAdjacent = (for ((uri, mult) <- offensiveMults; t <- PokeAPI.typeByURI(uri)) yield t).toVector;
  
  override def toString = s"$name; $offensiveMults; " + super.toString;

}

object TypeRegistry extends ModelRegistry[Type] {
  
}