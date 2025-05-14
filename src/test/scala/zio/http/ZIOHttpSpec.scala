package zio.http

import zio.*
import zio.test.*

trait ZIOHttpSpec extends ZIOSpecDefault {
  override def aspects: Chunk[TestAspectPoly] =
    Chunk(TestAspect.timeout(60.seconds), TestAspect.timed, TestAspect.silentLogging, TestAspect.silent)
}
