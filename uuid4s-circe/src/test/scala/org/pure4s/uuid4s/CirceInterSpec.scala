package org.pure4s.uuid4s

import java.util.UUID

import cats.effect.IO
import io.circe.Decoder
import io.circe.Json
import org.scalatest._
import org.pure4s.uuid4s.circe._
import io.circe.syntax._
import org.scalatest.EitherValues._

class CirceInterSpec extends FunSpec with Matchers {

  val validUUIDAsString = "f94e2de4-1c08-4189-9664-105954589e52"
  val inValidUUIDAsString = "12396882-202e-4d75-a316-17d848a0112y"

  describe("Circe serialization") {
    it("Successfully completes serialization") {
      val circeUUID: UUID = FUUID[IO].fromString(validUUIDAsString).unsafeRunSync()
      val circeFUUIDJson: Json = circeUUID.asJson

      circeFUUIDJson shouldBe validUUIDAsString.asJson
    }
  }
  describe("Circe deserialization") {
    it("Successfully completes deserialization") {
      val circeUUID: UUID = FUUID[IO].fromString(validUUIDAsString).unsafeRunSync()
      val circeFUUIDJson: Json = circeUUID.asJson
      val decode: Decoder.Result[UUID] = circeFUUIDJson.as[UUID]

      decode.right.value.toString shouldBe validUUIDAsString
    }
  }
}