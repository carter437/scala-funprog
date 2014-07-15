import scala.xml.XML

object Yo extends App{
  var set = scala.collection.mutable.Set[String]()
  val xml = XML.loadFile("/Users/croughton/Downloads/alsekcruisecharts_current/alsek_cruisecharts.xml")
  (xml \\ "Departure").foreach(d => set.add((d \ "@OperatorName").text))
  set.foreach(println)
}