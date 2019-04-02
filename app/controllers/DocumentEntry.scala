package controllers

import akka.actor.Status.Success
import com.mongodb.BasicDBObject
import org.bson.types.ObjectId

import scala.util
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.model.BsonField
import com.mongodb.client.model.Filters.eq

import scala.util.parsing.json.JSONObject

case class Doc(_id: ObjectId, title: String, body: String)

object Doc {
  implicit val ec: ExecutionContext = ExecutionContext.global
  lazy val mongoClient = MongoClient()
  lazy val database = mongoClient.getDatabase("documents")
  lazy val collection = database.getCollection("document")

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
    val readDocuments = collection.find().toFuture().map{
      case x: Seq[Document] =>
        println(s"Num of documents = ${x.length}")
        x.map(Doc.from)
      case _ => Nil
    }

    Await.result(readDocuments, Duration.Inf)
  }

  def getById(id: String): Option[Doc] = {
    val query = new BasicDBObject()
    query.put("_id", new ObjectId(id))

    Await.result(
      collection.find(query).toFuture().map{_.map(Doc.from).headOption},
      Duration.Inf
    )
  }

  def deleteById(id: String): Unit = {
    val query = new BasicDBObject()
    query.put("_id", new ObjectId(id))

    Await.result(
      collection.findOneAndDelete(query).toFuture(),
      Duration.Inf
    )
  }
}



