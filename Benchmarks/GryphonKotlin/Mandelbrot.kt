
class Mandelbrot {
	companion object {
		var n: Int = 0
		fun run(n: Int = 200): String {
		    val Crb = DoubleArray(size = n + 7)
		    val invN = 2.0 / n
			for (i in (0 until n)) {
				Crb[i] = i * invN - 1.5
			}
			val lineLen = (n - 1) / 8 + 1
			val output = UByteArray(size = n * lineLen)
			for (y in (0 until n)) {
				val Ciby: Double = y * invN - 1.0
				val offset = y * lineLen;
				for (x in (0 until lineLen)) {
					output[offset + x] = getByte(Crb, Ciby, x * 8)
				}
			}
		    var result = ""
		    for (item in output) {
		    	result += "$item"
		    }
			return result
		}
		fun getByte(Crb: DoubleArray, CibY: Double, x: Int): UByte {
		    var res = 0
		    var i = 0
		    while (i < 8) {
		        var Zr1 = Crb[x + i]
		        var Zi1 = CibY
		        var Zr2 = Crb[x + i + 1]
		        var Zi2 = CibY
		        var b = 0
		        var j = 49
		        do {
		            val nZr1 = Zr1 * Zr1 - Zi1 * Zi1 + Crb[x + i]
		            Zi1 = Zr1 * Zi1 + Zr1 * Zi1 + CibY
		            Zr1 = nZr1
		            val nZr2 = Zr2 * Zr2 - Zi2 * Zi2 + Crb[x + i + 1]
		            Zi2 = Zr2 * Zi2 + Zr2 * Zi2 + CibY
		            Zr2 = nZr2
		            if (Zr1 * Zr1 + Zi1 * Zi1 > 4) {
		            	b = b or 2
		            	if (b == 3) {
		            		break
		            	}
		            }
		            if (Zr2 * Zr2 + Zi2 * Zi2 > 4) {
		            	b = b or 1
		            	if (b == 3) {
		            		break
		            	}
		            }
		            j -= 1
		        } while(j > 0)
		        res = (res shl 2) + b
		        i += 2
		    }
		    return (res xor (-1)).toUByte()
		}
	}
}
