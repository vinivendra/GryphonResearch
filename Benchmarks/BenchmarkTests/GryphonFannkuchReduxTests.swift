import XCTest

class GryphonFannkuchReduxTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = "73196\nPfannkuchen(10) = 38"

        let result = GryphonFannkuchRedux.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            _ = GryphonFannkuchRedux.run(n: 9)
        }
    }
}
