package pokescala.model

import java.time.LocalDateTime

class Move(
    val name : String,
    val description : String,
    val power : Int,
    val accuracy : Int,
    val category : String,
    val pp : Int,
    val id : Int,
    val resourceURI : String,
    val created : LocalDateTime,
    val modified : LocalDateTime) extends Model[Move] {

  val registry = MoveRegistry;
  registry.register(this);
  
  def loadAdjacent = Vector();
  
  override def toString = s"$name; $description; $power; $accuracy; $category; $pp; " + super.toString;
  
}

object Move {
  object Category {
    
    def apply(raw : String) : String = {
      if (raw equals physical)
        return physical;
      if (raw equals special)
        return special;
      return other;
    }
    
    val physical = "physical";
    val special = "special";
    val other = "other";
  }
}

object MoveRegistry extends ModelRegistry[Move] {
  
}