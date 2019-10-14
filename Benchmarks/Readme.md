# Benchmarks

There are two benchmark programs. The Swift program can be opened by the Xcode project file. The Kotlin program is contained in the GryphonKotlin folder, and can be compiled and run using the `buildAndRun.sh` script there.

Both programs contain unit tests. Swift's tests can be run through Xcode. Kotlin's tests can be run by uncommenting the appropriate lines in the main.kt file.

Running the programs will make them start running batches of 100 benchmarks run and saving the results to the Results folder. The results are all in the number of seconds it took for each individual run to complete. Both the main.swift and the main.kt files should be edited to include valid paths to the Results folder before running. They will also run indefinitely, and should be manually interrupted by the user once enough data has been recorded.

Running the `transpileBenchmarks.sh` script will convert the Swift code into Kotlin code. For that the script must be edited to contain a valid path to the Gryphon executable.

Data was collected by running each program separately on a MacBook Pro (Retina, 15-inch, Mid 2015) with a 2.5GHz Intel Core i7 processor, 16GB 1600 MHz DDR3 Ram, charged to 100% battery and connected to a power outlet, while no other applications were running.


