class BinaryTreesTest: Test("BinaryTreesTest") {
	override fun runAllTests() {
		testCorrectness()
		super.runAllTests()
	}

	fun testCorrectness() {
		val expectedResult = 
			"stretch tree of depth 22	 check: 8388607\n" + 
			"2097152	 trees of depth 4	 check: 65011712\n" + 
			"524288	 trees of depth 6	 check: 66584576\n" + 
			"131072	 trees of depth 8	 check: 66977792\n" + 
			"32768	 trees of depth 10	 check: 67076096\n" + 
			"8192	 trees of depth 12	 check: 67100672\n" + 
			"2048	 trees of depth 14	 check: 67106816\n" + 
			"512	 trees of depth 16	 check: 67108352\n" + 
			"128	 trees of depth 18	 check: 67108736\n" + 
			"32	 trees of depth 20	 check: 67108832\n" + 
			"long lived tree of depth 21	 check: 4194303\n"

		val result = GryphonBinaryTrees.run(n = 21)
		XCTAssertEqual(expectedResult, result)
	}
}
