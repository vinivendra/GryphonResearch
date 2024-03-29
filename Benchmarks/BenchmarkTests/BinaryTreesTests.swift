import XCTest

class BinaryTreesTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = """
stretch tree of depth 11	 check: 4095
1024	 trees of depth 4	 check: 31744
256	 trees of depth 6	 check: 32512
64	 trees of depth 8	 check: 32704
16	 trees of depth 10	 check: 32752
long lived tree of depth 10	 check: 2047

"""

        let result = BinaryTrees.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            for _ in 0..<50 {
                _ = BinaryTrees.run()
            }
        }
    }
}
