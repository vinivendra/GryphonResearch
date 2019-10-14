import Foundation

let batchSize = 100

let startingBatchNumber = 7

var printResult: [String] = [String](repeating: "", count: batchSize)

for i in startingBatchNumber... {
    print("Batch number \(i)")

    print("\t- Binary trees")
    print("\t\t- Gryphon")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = GryphonBinaryTrees.run(n: 16)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftGryphonBinaryTrees\(i).txt", atomically: true, encoding: .utf8)
    print("\t\t- Original")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = BinaryTrees.run(n: 16)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftOriginalBinaryTrees\(i).txt", atomically: true, encoding: .utf8)

    print("\t- Fannkuch")
    print("\t\t- Gryphon")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = GryphonFannkuchRedux.run(n: 10)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftGryphonFannkuch\(i).txt", atomically: true, encoding: .utf8)
    print("\t\t- Original")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = FannkuchRedux.run(n: 10)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftOriginalFannkuch\(i).txt", atomically: true, encoding: .utf8)

    print("\t- Mandelbrot")
    print("\t\t- Gryphon")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = GryphonMandelbrot.run(n: 4000)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftGryphonMandelbrot\(i).txt", atomically: true, encoding: .utf8)
    print("\t\t- Original")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = Mandelbrot.run(n: 4000)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftOriginalMandelbrot\(i).txt", atomically: true, encoding: .utf8)

    print("\t- N Body")
    print("\t\t- Gryphon")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = GryphonNBody.run(n: 1000000)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftGryphonNBody\(i).txt", atomically: true, encoding: .utf8)
    print("\t\t- Original")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = NBody.run(n: 1000000)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftOriginalNBody\(i).txt", atomically: true, encoding: .utf8)

    print("\t- Spectral norm")
    print("\t\t- Gryphon")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = GryphonSpectralNorm.run(n: 1500)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftGryphonSpectralNorm\(i).txt", atomically: true, encoding: .utf8)
    print("\t\t- Original")
    for i in 0..<batchSize {
        let start = Date().timeIntervalSince1970
        _ = SpectralNorm.run(n: 1500)
        let end = Date().timeIntervalSince1970

        let result = "\(end - start)"
        printResult[i] = result
    }
    try! printResult.joined(separator: "\n").write(toFile: "/path/to/Results/swiftOriginalSpectralNorm\(i).txt", atomically: true, encoding: .utf8)

}

// Spectral norm (1500) -> 2.9s
// N body (1000000) -> 4.0s
// Fannkuch (10) -> 9.4s
// Mandelbrot (4000) -> 3.8s
// BinaryTrees (16) -> 3.6s
