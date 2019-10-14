class SpectralNorm {
	companion object {
		var n: Int = 0
		fun run(n: Int = 5500): String {
			SpectralNorm.n = n
        	return spectralnormGame(n).toString()
		}
		fun spectralnormGame(n: Int): Double {
        	val u = DoubleArray(size = n)
        	val v = DoubleArray(size = n)
        	val tmp = DoubleArray(size = n)
	        for (i in (0 until n)) {
	            u[i] = 1.0
	        }
            var m_vBv = 0.0
            var m_vv = 0.0
            for (i in (0 until 10)) {
                multiplyAtAv(u, tmp, v)
                multiplyAtAv(v, tmp, u)
            }
            for (i in (0 until n)) {
                m_vBv += u[i] * v[i]
                m_vv  += v[i] * v[i]
            }
	        return Math.sqrt(m_vBv/m_vv)
		}
        private fun eval_A(i: Int, j: Int): Double {
            val div = (((i+j) * (i+j+1) / 2) + i + 1)
            return 1.0 / div
        }
        private fun multiplyAv(v: DoubleArray, Av: DoubleArray) {
            for (i in (0 until n)) {
                var sum = 0.0
                for (j in (0 until v.size)) {
                    sum += eval_A(i, j) * v[j]
                }
                Av[i] = sum
            }
        }
        private fun multiplyAtv(v: DoubleArray, Atv: DoubleArray) {
            for (i in (0 until n)) {
                var sum = 0.0
                for (j in (0 until v.size)) {
                    sum += eval_A(j, i) * v[j]
                }
                Atv[i] = sum;
            }
        }
        private fun multiplyAtAv(
        	v: DoubleArray,
        	tmp: DoubleArray,
        	AtAv: DoubleArray)
        {
            multiplyAv (v, tmp)
            multiplyAtv (tmp, AtAv)
        }
	}
}