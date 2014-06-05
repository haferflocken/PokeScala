package pokescala.parse

import scala.util.parsing.json.JSONObject
import pokescala.model.Model
import java.time.LocalDateTime
import scala.util.parsing.json.JSON

abstract class Parser[T <: Model[T]] {
  
  def parse(implicit raw : Map[String, Any]) : T;
  
  def parse(raw : JSONObject) : T = parse(JSONConverter.objToMap(raw));
  
  def parse(raw : String) : T = parse(JSON.parseRaw(raw).get.asInstanceOf[JSONObject]);
  
  protected implicit def asVector(a : Any) : Vector[Any] = {
    
    if (a.isInstanceOf[Vector[Any]])
      return a.asInstanceOf[Vector[Any]];
    
    return Vector(a);
  }
  
  protected def extract[T](key : String)(implicit raw : Map[String, Any]) : T = raw(key).asInstanceOf[T];
  
  protected def extractModelInfo(idKey : String = "id")(implicit raw : Map[String, Any]) : (Int, String, LocalDateTime, LocalDateTime) = {
    val id = extract[Double](idKey).asInstanceOf[Int];
    val resourceURI = extract[String]("resource_uri");
    val created = LocalDateTime.parse(extract[String]("created"));
    val modified = LocalDateTime.parse(extract[String]("modified"));
    
    return (id, resourceURI, created, modified);
  };
  
  protected def extractResourceURIs(raw : Vector[Any]) : Vector[String] = 
    raw.map(e => e.asInstanceOf[Map[String, Any]]("resource_uri").asInstanceOf[String]);
  
}
