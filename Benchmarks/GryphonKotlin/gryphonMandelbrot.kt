open class GryphonMandelbrot {
    companion object {
        var n: Int = 0
        internal fun run(n: Int = 200): String {
            var Crb: DoubleArray = DoubleArray(size = n + 7)
            val invN: Double = 2.0 / n
            for (i in 0 until n) {
                Crb[i] = i * invN - 1.5
            }
            val lineLen: Int = (n - 1) / 8 + 1
            var output: UByteArray = UByteArray(size = n * lineLen)
            for (y in 0 until n) {
                val Ciby: Double = y * invN - 1.0
                val offset: Int = y * lineLen
                for (x in 0 until lineLen) {
                    output[offset + x] = getByte(Crb = Crb, CibY = Ciby, x = x * 8)
                }
            }
            var result: String = ""
            for (item in output) {
                result += "${item}"
            }
            return result
        }
        internal fun getByte(Crb: DoubleArray, CibY: Double, x: Int): UByte {
            var res: Int = 0
            var i: Int = 0
            while (i < 8) {
                var Zr1: Double = Crb[x + i]
                var Zi1: Double = CibY
                var Zr2: Double = Crb[x + i + 1]
                var Zi2: Double = CibY
                var b: Int = 0
                var j: Int = 49
                while (true) {
                    val nZr1: Double = Zr1 * Zr1 - Zi1 * Zi1 + Crb[x + i]
                    Zi1 = Zr1 * Zi1 + Zr1 * Zi1 + CibY
                    Zr1 = nZr1
                    val nZr2: Double = Zr2 * Zr2 - Zi2 * Zi2 + Crb[x + i + 1]
                    Zi2 = Zr2 * Zi2 + Zr2 * Zi2 + CibY
                    Zr2 = nZr2
                    if ((Zr1 * Zr1 + Zi1 * Zi1 > 4.0)) {
                        b = b or 2
                        if ((b == 3)) {
                            break
                        }
                    }
                    if ((Zr2 * Zr2 + Zi2 * Zi2 > 4.0)) {
                        b = b or 1
                        if ((b == 3)) {
                            break
                        }
                    }
                    j -= 1
                    if (j <= 0) {
                        break
                    }
                }
                res = (res shl 2) + b
                i += 2
            }
            return (res xor (-1)).toUByte()
        }
    }
}
