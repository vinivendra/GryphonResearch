import Darwin
class GryphonSpectralNorm {
    static var n: Int = 0
    static func run(n: Int = 5500) -> String {
        GryphonSpectralNorm.n = n
        return "\(approximate(n))" // value: approximate(n).toString()
    }
    static func A(_ i: Int, _ j: Int) -> Double {
        let divisor: Double = Double((i+j)*(i+j+1)/2 + (i+1)) // kotlin: ignore
        // insert: val divisor = (((i+j) * (i+j+1) / 2) + i + 1)
        return 1.0 / divisor
    }
    private static func multiplyAv(_ v: DoubleArray, _ Av: inout DoubleArray) {
        let n = GryphonSpectralNorm.n
        for i in 0..<n {
            var AvTemp = 0.0
            for j in 0..<n {
                AvTemp += A(i,j) * v[j]
            }
            Av[i] = AvTemp
        }
    }
    private static func multiplyAtv(_ v: DoubleArray, _ Atv: inout DoubleArray) {
        let n = GryphonSpectralNorm.n
        for i in 0..<n {
            var AtvTemp = 0.0
            for j in 0..<n {
                AtvTemp += A(j,i) * v[j]
            }
            Atv[i] = AtvTemp
        }
    }
    private static func multiplyAtAv(
        _ v: DoubleArray,
        _ temp: inout DoubleArray,
        _ AtAv: inout DoubleArray)
    {
        multiplyAv(v, &temp)
        return multiplyAtv(temp, &AtAv)
    }
    static func approximate(_ n: Int) -> Double {
        var u = [Double](repeating: 1.0, count: n) // kotlin: ignore
        var v = [Double](repeating: 1.0, count: n) // kotlin: ignore
        var temp = [Double](repeating: 1.0, count: n) // kotlin: ignore
        // insert: var u = DoubleArray(size = n)
        // insert: var v = DoubleArray(size = n)
        // insert: var temp = DoubleArray(size = n)
        // insert:
        // insert: for (i in (0 until n)) {
        // insert:     u[i] = 1.0
        // insert: }
        var vBv = 0.0
        var vv = 0.0
        for _ in 0..<10 {
            multiplyAtAv(u, &temp, &v)
            multiplyAtAv(v, &temp, &u)
        }
        for i in 0..<n {
            vBv += u[i]*v[i]
            vv += v[i]*v[i]
        }
        return sqrt(vBv/vv)
    }
}
