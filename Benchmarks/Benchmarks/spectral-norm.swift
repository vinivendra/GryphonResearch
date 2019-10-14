import Darwin
class SpectralNorm {
    static var n: Int = 0
    static func run(n: Int = 5500) -> String {
        GryphonSpectralNorm.n = n
        return "\(approximate(n))"
    }
    static func A(_ i: Int, _ j: Int) -> Double {
        return 1.0 / Double((i+j)*(i+j+1)/2 + (i+1))
    }
    private static func multiplyAv(_ v: [Double], _ Av: inout [Double]) {
        let n = GryphonSpectralNorm.n
        for i in 0..<n {
            var AvTemp = 0.0
            for j in 0..<n {
                AvTemp += A(i,j) * v[j]
            }
            Av[i] = AvTemp
        }
    }
    private static func multiplyAtv(_ v: [Double], _ Atv: inout [Double]) {
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
        _ v: [Double],
        _ temp: inout [Double],
        _ AtAv: inout [Double])
    {
        multiplyAv(v, &temp)
        return multiplyAtv(temp, &AtAv)
    }
    static func approximate(_ n: Int) -> Double {
        var u = [Double](repeating: 1.0, count: n)
        var v = [Double](repeating: 1.0, count: n)
        var temp = [Double](repeating: 1.0, count: n)
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
