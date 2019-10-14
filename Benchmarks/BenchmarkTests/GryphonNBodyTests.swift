import XCTest

class GryphonNBodyTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = "-0.16907516382852447\n-0.169087605234606\n"

        let result = GryphonNBody.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            for _ in 0..<1000 {
                _ = GryphonNBody.run()
            }
        }
    }
}
