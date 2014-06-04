package pokescala.parse

import scala.util.parsing.json.JSONObject
import pokescala.model.Model
import java.time.LocalDateTime

abstract class Parser[T <: Model[T]] {
  
  def parse(implicit raw : Map[String, Any]) : T;
  
  def parse(raw : JSONObject) : T = parse(JSONTreeConverter.objToMap(raw));
  
  protected implicit def asVector(a : Any) : Vector[Any] = {
    if (a.isInstanceOf[Vector[Any]])
      return a.asInstanceOf[Vector[Any]];
    
    return Vector(a);
  }
  
  protected def extract[T](key : String)(implicit raw : Map[String, Any]) : T = raw(key).asInstanceOf[T];
  
  protected def extractModelInfo(raw : Map[String, Any], idKey : String = "id") : (Int, String, LocalDateTime, LocalDateTime) = {
    val id = raw(idKey).asInstanceOf[Int];
    val resourceURI = raw("resource_uri").asInstanceOf[String];
    val created = LocalDateTime.parse(raw("created").asInstanceOf[String]);
    val modified = LocalDateTime.parse(raw("modified").asInstanceOf[String]);
    
    return (id, resourceURI, created, modified);
  };
  
  protected def extractResourceURIs(raw : Vector[Any]) : Vector[String] = 
    raw.map(e => e.asInstanceOf[Map[String, Any]]("resource_uri").asInstanceOf[String]);
  
}
