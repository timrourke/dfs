package example

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import example.DepthFirstSearch.pathExists

class DepthFirstSearchSpec extends AnyFunSpec with Matchers {
  private[this] val largerGraph: Seq[(String, String)] = List(
    "A" -> "B",
    "B" -> "C",
    "B" -> "E",
    "C" -> "D",
    "E" -> "A",
    "F" -> "D"
  )

  describe("DepthFirstSearch") {
    it("should not find a connection in an empty graph") {
      pathExists("a", "b", Seq()) shouldBe false
    }

    it("should find a connection in a graph containing exactly the nodes searched for") {
      pathExists("a", "b", Seq("a" -> "b")) shouldBe true
    }

    it("should find a connection in a graph containing exactly the nodes searched for, in reverse") {
      pathExists("a", "b", Seq("b" -> "a")) shouldBe true
    }

    it("should not find a connection in a graph where no connections exist") {
      pathExists("a", "b", Seq("a" -> "c", "b" -> "c")) shouldBe false
    }

    it("should find a multi-step connection in a larger graph") {
      pathExists("A", "E", largerGraph) shouldBe true
    }

    it("should not find a connection in a larger graph where no connections exist") {
      pathExists("A", "F", largerGraph) shouldBe false
    }
  }
}
