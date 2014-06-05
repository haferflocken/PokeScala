package pokescala.parse

import pokescala.model._
import pokescala.model.Implicits._
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable
import scala.util.Try

object TypeParser extends Parser[Type] {
  
  def parse(implicit raw : Map[String, Any]) : Try[Type] = Try {
    val (id, resourceURI, created, modified) = extractModelInfo();
    val name = extract[String]("name");
    
    val strongAgainst = extractResourceURIs(raw("super_effective"));
    val weakAgainst = extractResourceURIs(raw("ineffective"));
    val noEffectAgainst = extractResourceURIs(raw("no_effect"));
    
    val offensiveMultsBuff = new mutable.HashMap[String, Double];
    offensiveMultsBuff ++= strongAgainst.map(t => (t, 2.0));
    offensiveMultsBuff ++= weakAgainst.map(t => (t, 0.5));
    offensiveMultsBuff ++= noEffectAgainst.map(t => (t, 0.0));
    
    val offensiveMults = offensiveMultsBuff.toMap;
    
    new Type(name, offensiveMults, id, resourceURI, created, modified);
  };

}