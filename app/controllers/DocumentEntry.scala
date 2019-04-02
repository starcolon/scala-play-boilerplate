package controllers

import akka.actor.Status.Success

import scala.util
import scala.concurrent._
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext
import org.mongodb.scala._
import org.mongodb.scala.bson.ObjectId

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

//
//
//      .value.map{
//      case util.Success(output) =>
//        println("Output = ")
//        println(output)
//        output
//      case util.Failure(e) =>
//        println("Error parsing documents")
//        println(e)
//        Nil
//    }.getOrElse(Nil)
  }
}



