class FannkuchReduxTest: Test("FannkuchReduxTest") {
	override fun runAllTests() {
		testCorrectness()
		super.runAllTests()
	}

	fun testCorrectness() {
		val expectedResult = "73196\nPfannkuchen(10) = 38"
		val result = GryphonFannkuchRedux.run()
		XCTAssertEqual(expectedResult, result)
	}
}
