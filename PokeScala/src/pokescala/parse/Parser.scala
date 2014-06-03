package pokescala.parse

import scala.util.parsing.json.JSONObject
import pokescala.model.Model
import java.time.LocalDateTime

abstract class Parser[T <: Model[T]] {
  
  def parse(raw : Map[String, Any]) : T;
  
  def parse(raw : JSONObject) : T = parse(JSONTreeConverter.objToMap(raw));
  
  protected def extractModelInfo(raw : Map[String, Any], idKey : String = "id") : (Int, String, LocalDateTime, LocalDateTime) = {
    val id = raw(idKey).asInstanceOf[Int];
    val resourceURI = raw("resource_uri").asInstanceOf[String];
    val created = LocalDateTime.parse(raw("created").asInstanceOf[String]);
    val modified = LocalDateTime.parse(raw("modified").asInstanceOf[String]);
    
    return (id, resourceURI, created, modified);
  };
  
}
