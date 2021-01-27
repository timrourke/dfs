package example

object DepthFirstSearch {
  def pathExists(
      from: String,
      to: String,
      edges: Seq[(String, String)]
  ): Boolean = {
    def next(
        currentEdge: (String, String),
        seenNodes: Set[String],
        remainingEdges: Seq[(String, String)]
    ): Boolean = {
      if (seenNodes.contains(from) && seenNodes.contains(to)) {
        true
      } else {
        remainingEdges.exists(e => {
          next(
            e,
            seenNodes + e._2,
            remainingEdges.filter(e => e != currentEdge)
          )
        })
      }
    }

    if (edges.isEmpty) {
      false
    } else if (edges.filter(_._1 == from).exists(_._2 == to)) {
      true
    } else {
      next(
        edges.head,
        Set(edges.head._1, edges.head._2),
        edges.filter(e => e != edges.head)
      )
    }
  }
}
