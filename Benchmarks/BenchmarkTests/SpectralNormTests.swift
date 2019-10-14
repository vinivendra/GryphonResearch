import XCTest

class SpectralNormTests: XCTestCase {
    func testCorrectness() {
        let expectedResult = "1.2742241527925133"

        let result = SpectralNorm.run()

        XCTAssertEqual(expectedResult, result)
    }

    func testPerformance() {
        measure {
            _ = SpectralNorm.run(n: 1500)
        }
    }
}
