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
  
}

object MoveCategory {
  
}

object MoveRegistry extends ModelRegistry[Move] {
  
}