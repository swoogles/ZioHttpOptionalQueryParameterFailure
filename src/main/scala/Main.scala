import zio.http.codec.{HttpCodec, QueryCodec}

object Main extends App {
  val queryCodec: QueryCodec[Option[String]] = 
    HttpCodec.query[Option[String]]("messageStateIds")
  println(
    "Encoded: " +
      queryCodec.encodeRequest(Some("justEncodeIt"))
  )
}
