package controllers

import javax.inject.Inject

import play.api._
import play.api.libs.json.Json
import play.api.mvc._

class DocumentController @Inject()(cc: ControllerComponents)() extends AbstractController(cc) {
  def index = Action {
    Ok(Json.obj("Message" -> "Please implement me!"))
  }
}
