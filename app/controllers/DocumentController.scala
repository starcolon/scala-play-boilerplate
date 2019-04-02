package controllers

import javax.inject.Inject

import play.api._
import play.api.libs.json.Json
import play.api.mvc._

class DocumentController @Inject()
(cc: ControllerComponents)() extends AbstractController(cc) {
  // Root 
  def index = Action {
    Ok(Json.obj("Message" -> "Please implement me!"))
  }

  def listDocuments() = Action {
    val documents = Doc.getAll()
    println("Documents ::")
    documents.foreach{println}
    Ok(Json.obj("Message" -> documents.map(_.toString).mkString(", ")))
  }

  def getDocument(id: String) = Action {
    Ok(Json.obj("Message" -> "Please input something"))
  }

  def createDocument() = Action {
    Ok(Json.obj("Message" -> "Please input something"))
  }

  def updateDocument(id: String) = Action {
    Ok(Json.obj("Message" -> "Please input something"))
  }

  def deleteDocument(id: String) = Action {
    Ok(Json.obj("Message" -> "Please input something"))
  }
}
