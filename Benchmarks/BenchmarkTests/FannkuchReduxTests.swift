import XCTest

class FannkuchReduxTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = "73196\nPfannkuchen(10) = 38"

        let result = FannkuchRedux.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            _ = FannkuchRedux.run(n: 9)
        }
    }
}
