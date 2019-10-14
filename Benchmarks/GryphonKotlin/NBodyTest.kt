
class NBodyTest: Test("NBodyTest") {
	override fun runAllTests() {
		testCorrectness()
		super.runAllTests()
	}

	fun testCorrectness() {
		val expectedResult = "-0.16907516382852447\n-0.169087605234606\n"
		val result = GryphonNBody.run()
		XCTAssertEqual(expectedResult, result)
	}
}
