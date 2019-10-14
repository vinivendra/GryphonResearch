open class Test(val className: String) {
	companion object {
		var allTestsSucceeded = true
	}

	var allTestsSucceeded = true

	open fun runAllTests() {
		if (allTestsSucceeded) {
			println("${className}: All tests succeeded!")
		}
	}

	fun XCTAssert(condition: Boolean, test: String = "No message") {
		if (!condition) {
			allTestsSucceeded = false
			Test.allTestsSucceeded = false

			println("${className} - XCTAssert failed: ${test}.")
			Exception().printStackTrace()
		}
	}

	fun XCTAssertFalse(condition: Boolean, test: String = "No message") {
		if (condition) {
			allTestsSucceeded = false
			Test.allTestsSucceeded = false

			println("${className} - XCTAssertFalse failed: ${test}.")
			Exception().printStackTrace()
		}
	}

	fun <T> XCTAssertEqual(a: T, b: T, test: String = "No message") {
    	if (a != b) {
    		allTestsSucceeded = false
			Test.allTestsSucceeded = false
			
    		println("${className} - XCTAssertEqual failed: ${test}.\n\"${a}\"\nis not equal to\n\"${b}\"\n--")
    		Exception().printStackTrace()
    	}
	}

	fun <T> XCTAssertNotEqual(a: T, b: T, test: String = "No message") {
    	if (a == b) {
    		allTestsSucceeded = false
			Test.allTestsSucceeded = false
			
    		println("${className} - XCTAssertNotEqual failed: ${test}.\n\"${a}\"\nis equal to\n\"${b}\"\n--")
    		Exception().printStackTrace()
    	}
	}

	fun <T> XCTAssertNil(a: T?, test: String = "No message") {
    	if (a != null) {
    		allTestsSucceeded = false
			Test.allTestsSucceeded = false
			
    		println("${className} - XCTAssertNil failed: ${test}.")	
    		Exception().printStackTrace()
    	}
	}

	fun XCTFail(test: String = "No message") {
    	allTestsSucceeded = false
		Test.allTestsSucceeded = false
			
		println("${className} - XCTFail: ${test}.")	
		Exception().printStackTrace()
	}

	fun XCTAssertNoThrow(closure: () -> Unit, test: String = "No message") {
		try {
			closure()
    	}
    	catch (error: Exception) {
    		allTestsSucceeded = false
			Test.allTestsSucceeded = false
			
    		println("${className} - XCTAssertNoThrow failed: ${test}.")
    		Exception().printStackTrace()
    		println("Error thrown:")
    		println(error)
    	}
	}
}
