import XCTest

class GryphonSpectralNormTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = "1.2742241527925133"

        let result = GryphonSpectralNorm.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            _ = GryphonSpectralNorm.run(n: 1500)
        }
    }
}
