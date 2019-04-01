package controllers

import org.mongodb._
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId

import scala.concurrent.ExecutionContext

case class Doc(_id: ObjectId, title: String, body: String)

object Doc {
  def apply(title: String, body: String): Doc = 
    Doc(new ObjectId(), title, body)

  def from(doc: Document): Doc = {
    Doc(
      doc.getObjectId("_id"),
      doc.getString("title"),
      doc.getString("body")
    )
  }

  def getAll(): Seq[Doc] = {
    implicit val ec: ExecutionContext = ExecutionContext.global

    val mongoClient = MongoClient()
    val database = mongoClient.getDatabase("documents")
    val collection = database.getCollection("document")
    collection.find().toFuture().map{
      case x: Seq[Document] => x.map(Doc.from)
      case _ => Nil
    }.value.getOrElse(Nil)
  }
}



