private data class TreeNode(
	var left: TreeNode? = null,
    var right: TreeNode? = null)
{
    var item: Int = 0
}
class BinaryTrees {
	companion object {
		var n: Int = 0
		fun run(n: Int = 21): String {
			BinaryTrees.n = n
			var maxDepth = if (6 > n) { 6 } else { n }
            val stretchDepth = maxDepth + 1
            var result = "stretch tree of depth $stretchDepth \t check: ${checkTree(createTree(stretchDepth))}\n"
            result += trees(maxDepth)
        	return result
		}
		private fun trees(maxDepth: Int): String {
			var result = ""
	        val longLastingNode = createTree(maxDepth)
	        var depth = 4
	        do {
	            val iterations = 16 shl (maxDepth - depth)
	            result += loops(iterations, depth)
	            depth += 2
	        } while (depth <= maxDepth)
	        result += "long lived tree of depth $maxDepth\t check: ${checkTree(longLastingNode)}"
	        return result
	    }
	    private fun loops(iterations: Int, depth: Int): String {
	    	var check = 0;
	        var item = 0;
	        do {
	            check += checkTree(createTree(depth))
	            item++
	        } while (item < iterations)
	        return "$iterations\t trees of depth $depth\t check: $check\n"
	    }
	    private fun createTree(depth: Int): TreeNode {
	    	if (depth > 0) {
    	       return TreeNode(createTree(depth-1),
    	                       createTree(depth-1))
    	   } else {
    	       return TreeNode(null, null)
    	   }
	    }
	    private fun checkTree(node: TreeNode?): Int {
	    	if (node?.left == null) {
	            return 1
	        }
	        return checkTree(node.left) + checkTree(node.right) + 1
	    }
	}
}
