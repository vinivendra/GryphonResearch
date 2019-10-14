open class GryphonTreeNode {
    var left: GryphonTreeNode? = null
    var right: GryphonTreeNode? = null
    constructor(left: GryphonTreeNode?, right: GryphonTreeNode?) {
        this.left = left
        this.right = right
    }
    internal fun check(): Int {
        if (left != null) {
            return left!!.check() + right!!.check() + 1
        }
        else {
            return 1
        }
    }
}
private fun createTree(depth: Int): GryphonTreeNode? {
    if (depth > 0) {
        val node: GryphonTreeNode = GryphonTreeNode(left = createTree(depth - 1), right = createTree(depth - 1))
        return node
    }
    else {
        val node: GryphonTreeNode = GryphonTreeNode(left = null, right = null)
        return node
    }
}
open class GryphonBinaryTrees {
    companion object {
        internal fun run(n: Int = 10): String {
            var returnResult: String = ""
            val minDepth: Int = 4
            val maxDepth: Int = if (n > minDepth + 2) { n } else { minDepth + 2 }
            val tree: GryphonTreeNode? = createTree(maxDepth + 1)
            val check: Int = tree!!.check()
            returnResult += "stretch tree of depth ${maxDepth + 1}\t check: ${check}\n"
            val longLivingTree: GryphonTreeNode? = createTree(maxDepth)
            var results = Array<String>(size = (maxDepth-minDepth)/2+1) { "" }
            var currentDepth: Int = minDepth
            while (currentDepth < maxDepth) {
                val idx: Int = (currentDepth - minDepth) / 2
                val iterations: Int = 1 shl (maxDepth - currentDepth + minDepth)
                var totalChecksum: Int = 0
                for (_0 in 1..iterations) {
                    val tree1: GryphonTreeNode? = createTree(currentDepth)
                    totalChecksum += tree1!!.check()
                }
                results[idx] = "${iterations}\t trees of depth ${currentDepth}\t check: ${totalChecksum}\n"
                currentDepth += 2
            }
            for (result in results) {
                returnResult += result
            }
            returnResult += "long lived tree of depth ${maxDepth}\t check: ${longLivingTree!!.check()}\n"
            return returnResult
        }
    }
}
