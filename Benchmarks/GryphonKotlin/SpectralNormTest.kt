class SpectralNormTest: Test("SpectralNormTest") {
	override fun runAllTests() {
		testCorrectness()
		super.runAllTests()
	}

	fun testCorrectness() {
		val expectedResult = "1.2742241527925133"
		val result = GryphonSpectralNorm.run()
		XCTAssertEqual(expectedResult, result)
	}
}
