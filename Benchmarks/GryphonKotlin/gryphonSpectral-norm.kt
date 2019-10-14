open class GryphonSpectralNorm {
    companion object {
        var n: Int = 0
        internal fun run(n: Int = 5500): String {
            GryphonSpectralNorm.n = n
            return approximate(n).toString()
        }
        internal fun A(i: Int, j: Int): Double {
            val divisor = (((i+j) * (i+j+1) / 2) + i + 1)
            return 1.0 / divisor
        }
        private fun multiplyAv(v: DoubleArray, Av: DoubleArray) {
            val n: Int = GryphonSpectralNorm.n
            for (i in 0 until n) {
                var AvTemp: Double = 0.0
                for (j in 0 until n) {
                    AvTemp += A(i, j) * v[j]
                }
                Av[i] = AvTemp
            }
        }
        private fun multiplyAtv(v: DoubleArray, Atv: DoubleArray) {
            val n: Int = GryphonSpectralNorm.n
            for (i in 0 until n) {
                var AtvTemp: Double = 0.0
                for (j in 0 until n) {
                    AtvTemp += A(j, i) * v[j]
                }
                Atv[i] = AtvTemp
            }
        }
        private fun multiplyAtAv(v: DoubleArray, temp: DoubleArray, AtAv: DoubleArray) {
            multiplyAv(v, temp)
            return multiplyAtv(temp, AtAv)
        }
        internal fun approximate(n: Int): Double {
            var u = DoubleArray(size = n)
            var v = DoubleArray(size = n)
            var temp = DoubleArray(size = n)
            for (i in (0 until n)) {
                u[i] = 1.0
            }
            var vBv: Double = 0.0
            var vv: Double = 0.0
            for (_0 in 0 until 10) {
                multiplyAtAv(u, temp, v)
                multiplyAtAv(v, temp, u)
            }
            for (i in 0 until n) {
                vBv += u[i] * v[i]
                vv += v[i] * v[i]
            }
            return Math.sqrt(vBv / vv)
        }
    }
}
