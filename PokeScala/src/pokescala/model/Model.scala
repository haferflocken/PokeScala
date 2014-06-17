package pokescala.model

import java.time.LocalDateTime
import scala.collection.mutable

abstract class Model[M <: Model[M]] {
  
  val id : Int;
  val resourceURI : String;
  val created : LocalDateTime;
  val modified : LocalDateTime;
  val registry : ModelRegistry[M];
  
  def loadAdjacent : Vector[Model[_]];
  
  override def hashCode = id.hashCode;
  
  override def equals(other : Any) = hashCode == other.hashCode;
  
  override def toString = s"$id; $resourceURI; $created; $modified";

}

abstract class ModelRegistry[M <: Model[M]] {
  
  protected val byId = new mutable.HashMap[Int, M];
  protected val byResourceURI = new mutable.HashMap[String, M];
  
  def apply(id : Int) : M = byId(id);
  
  def apply(uri : String) : M = byResourceURI(uri);
  
  def contains(id : Int) : Boolean = byId.contains(id);
  
  def contains(uri : String) : Boolean = byResourceURI.contains(uri);
  
  def register(instance : M) : Unit = {
    byId(instance.id) = instance;
    byResourceURI(instance.resourceURI) = instance;
  }
  
  def size = byId.size;
  
  override def toString = byId.values.mkString("[ ", ", ", " ]");
  
}