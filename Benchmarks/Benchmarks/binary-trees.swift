private class TreeNode {
    var left, right: TreeNode?
    init(left: TreeNode?, right: TreeNode?) {
        self.left = left
        self.right = right
    }
    func check() -> Int {
        if left != nil {
            return left!.check() + right!.check() + 1
        } else {
            return 1
        }
    }
}
private func createTree(_ depth: Int) -> TreeNode? {
    if depth > 0 {
        let node = TreeNode(left: createTree(depth-1),
                            right: createTree(depth-1))
        return node
    } else {
        let node = TreeNode(left: nil, right: nil)
        return node
    }
}
class BinaryTrees {
    static func run(n: Int = 10) -> String {
        var returnResult = ""
        let minDepth = 4
        let maxDepth = (n > minDepth + 2) ? n : minDepth + 2
        let tree = createTree(maxDepth+1)
        let check = tree!.check()
        returnResult += "stretch tree of depth \(maxDepth+1)\t check: \(check)\n"
        let longLivingTree = createTree(maxDepth)
        var results = [String](repeating: "", count: (maxDepth-minDepth)/2+1)
        var currentDepth = minDepth
        while currentDepth < maxDepth {
            let idx = (currentDepth - minDepth) / 2
            let iterations = 1 << (maxDepth - currentDepth + minDepth)
            var totalChecksum = 0
            for _ in 1...iterations {
                let tree1 = createTree(currentDepth)
                totalChecksum += tree1!.check()
            }
            results[idx] = "\(iterations)\t trees of depth \(currentDepth)\t check: \(totalChecksum)\n"
            currentDepth += 2
        }
        for result in results {
            returnResult += result
        }
        returnResult += "long lived tree of depth \(maxDepth)\t check: \(longLivingTree!.check())\n"
        return returnResult
    }
}
