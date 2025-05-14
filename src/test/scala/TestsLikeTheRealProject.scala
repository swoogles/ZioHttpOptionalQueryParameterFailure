import zio.http.{Handler, Routes, ZIOHttpSpec, int}
import zio.http.Method.GET
import zio.http.codec.HttpCodec
import zio.http.endpoint.Endpoint
import zio.test.*
import zio.http.endpoint.EndpointSpec.testEndpoint

object TestsLikeTheRealProject extends ZIOHttpSpec {
  def spec =
    test("multiple optional query parameters by parsing query parameters as Option[T]") {
      check(Gen.int, Gen.alphaNumericString, Gen.alphaNumericString) { (userId, key, value) =>
        val testRoutes = testEndpoint(
          Routes(
            Endpoint(GET / "users" / int("userId"))
              .query(HttpCodec.query[Option[String]]("key"))
              .query(HttpCodec.query[Option[String]]("value"))
              .out[String]
              .implementHandler {
                Handler.fromFunction { case (userId, key, value) =>
                  s"path(users, $userId, $key, $value)"
                }
              },
          ),
        ) _
        testRoutes(s"/users/$userId", s"path(users, $userId, None, None)") &&
          testRoutes(s"/users/$userId?key=&value=", s"path(users, $userId, Some(), Some())") &&
          testRoutes(s"/users/$userId?key=&value=$value", s"path(users, $userId, Some(), ${Some(value)})") &&
          testRoutes(
            s"/users/$userId?key=$key&value=$value",
            s"path(users, $userId, ${Some(key)}, ${Some(value)})",
          )
      }
    }

}
