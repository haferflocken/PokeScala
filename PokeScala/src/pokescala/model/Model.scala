package pokescala.model

import java.time.LocalDateTime
import scala.collection.mutable

abstract class Model[M <: Model[M]] {
  
  val id : Int;
  val resourceURI : String;
  val created : LocalDateTime;
  val modified : LocalDateTime;
  val registry : ModelRegistry[M];
  
  override def hashCode = id.hashCode;
  
  override def equals(other : Any) = hashCode == other.hashCode;

}

abstract class ModelRegistry[M <: Model[M]] {
  
  protected val registry = new mutable.HashMap[Int, M];
  
  def apply(id : Int) : M = registry(id);
  
  def register(instance : M) = registry(instance.id) = instance;
  
}