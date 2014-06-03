package pokescala.parse

import scala.util.parsing.json.JSONObject
import scala.util.parsing.json.JSONArray
import scala.collection.mutable

object JSONTreeConverter {
  
  def objToMap(obj : JSONObject) : Map[String, Any] = {
    if (obj == null || obj.obj == null)
      return null;
    
    val buff = new mutable.HashMap[String, Any];
    
    for ((key, value) <- obj.obj) {
      buff(key) = process(value);
    }
    
    return buff.toMap;
  };
  
  def arrayToVector(array : JSONArray) : Vector[Any] = {
    if (array == null || array.list == null)
      return null;
    
    val buff = new mutable.ArrayBuffer[Any];
    
    for (elem <- array.list) {
      buff += process(elem);
    }
    
    return buff.toVector;
  };
  
  def process(x : Any) : Any = {
    if (x.isInstanceOf[JSONObject])
        return objToMap(x.asInstanceOf[JSONObject]);
    if (x.isInstanceOf[JSONArray])
      return arrayToVector(x.asInstanceOf[JSONArray]);
    return x;
  }

}