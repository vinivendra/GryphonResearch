class Mandelbrot {
    static var n: Int = 0
    static func run(n: Int = 200) -> String {
        var Crb = [Double](repeating: 0.0, count: n + 7)
        let invN = 2.0 / Double(n)
        for i in 0..<n {
            Crb[i] = Double(i) * invN - 1.5
        }
        let lineLen = (n - 1) / 8 + 1
        var output = [UInt8](repeating: 0, count: n * lineLen)
        for y in 0..<n {
            let Ciby: Double = Double(y) * invN - 1.0
            let offset = y * lineLen
            for x in 0..<lineLen {
                output[offset + x] = getByte(Crb: Crb, CibY: Ciby, x: x * 8)
            }
        }
        var result = ""
        for item in output {
            result += "\(item)"
        }
        return result
    }
    static func getByte(Crb: [Double], CibY: Double, x: Int) -> UInt8 {
        var res = 0
        var i = 0
        while i < 8 {
            var Zr1 = Crb[x + i]
            var Zi1 = CibY
            var Zr2 = Crb[x + i + 1]
            var Zi2 = CibY
            var b = 0
            var j = 49
            while true {
                let nZr1 = Zr1 * Zr1 - Zi1 * Zi1 + Crb[x + i]
                Zi1 = Zr1 * Zi1 + Zr1 * Zi1 + CibY
                Zr1 = nZr1
                let nZr2 = Zr2 * Zr2 - Zi2 * Zi2 + Crb[x + i + 1]
                Zi2 = Zr2 * Zi2 + Zr2 * Zi2 + CibY
                Zr2 = nZr2
                if (Zr1 * Zr1 + Zi1 * Zi1 > 4) {
                    b = b | 2
                    if (b == 3) {
                        break
                    }
                }
                if (Zr2 * Zr2 + Zi2 * Zi2 > 4) {
                    b = b | 1
                    if (b == 3) {
                        break
                    }
                }
                j -= 1
                if j <= 0 {
                    break
                }
            }
            res = (res << 2) + b
            i += 2
        }
        return UInt8(res ^ (255))
    }
}
