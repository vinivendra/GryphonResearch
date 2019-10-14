import kotlin.system.exitProcess
import java.io.File

fun printArray(array: Array<String>, filePath: String) {
	val file = File(filePath)
	file.printWriter().use { output ->
		for (line in array) {
			output.println(line)
		}
	}
}

fun main(args: Array<String>) {
	// SpectralNormTest().runAllTests()
	// NBodyTest().runAllTests()
	// MandelbrotTest().runAllTests()
	// BinaryTreesTest().runAllTests()
	// FannkuchReduxTest().runAllTests()

	val batchSize = 100

	val startingBatchNumber = 11

	var printResult = Array<String>(size = batchSize) { "" }

	for (i in startingBatchNumber..9999999) {

		println("Batch number $i")

	    println("\t- Binary trees")
	    println("\t\t- Gryphon")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        GryphonBinaryTrees.run(n = 21)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinGryphonBinaryTrees$i.txt")
	    println("\t\t- Original")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        BinaryTrees.run(n = 21)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinOriginalBinaryTrees$i.txt")



	    println("\t- Fannkuch")
	    println("\t\t- Gryphon")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        GryphonFannkuchRedux.run(n = 11)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinGryphonFannkuchRedux$i.txt")
	    println("\t\t- Original")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        FannkuchRedux.run(n = 11)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinOriginalFannkuchRedux$i.txt")



	    println("\t- Mandelbrot")
	    println("\t\t- Gryphon")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        GryphonMandelbrot.run(n = 900)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinGryphonMandelbrot$i.txt")
	    println("\t\t- Original")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        Mandelbrot.run(n = 900)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinOriginalMandelbrot$i.txt")



	    println("\t- N Body")
	    println("\t\t- Gryphon")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        GryphonNBody.run(n = 50000000)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinGryphonNBody$i.txt")
	    println("\t\t- Original")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        NBody.run(n = 50000000)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinOriginalNBody$i.txt")



	    println("\t- Spectral norm")
	    println("\t\t- Gryphon")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        GryphonSpectralNorm.run(n = 5500)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinGryphonSpectralNorm$i.txt")
	    println("\t\t- Original")
	    for (i in (0 until batchSize)) {
	        val start = System.currentTimeMillis()
	        SpectralNorm.run(n = 5500)
	        val end = System.currentTimeMillis()

	        val result = "${(end - start) / 1000.0}"
	        printResult[i] = result
	    }
	    printArray(printResult, "/path/to/Results/kotlinOriginalSpectralNorm$i.txt")
	}

	// Spectral norm (5500) -> 5.5s
	// N body (50000000) -> 5.8s
	// Fannkuch (11) -> 2.1s
	// Mandelbrot (900) -> 4.4s
	// BinaryTrees (21) -> 3.9s

	if (!Test.allTestsSucceeded) {
		exitProcess(-1)
	}
}
