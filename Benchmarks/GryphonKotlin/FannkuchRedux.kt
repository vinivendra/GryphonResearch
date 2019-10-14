class FannkuchRedux {
	companion object {
		var NCHUNKS: Int = 150
	    var CHUNKSZ: Int = 0
	    var NTASKS: Int = 0
	    var n: Int = 0
	    var Fact: IntArray = IntArray(0)
	    var maxFlips: IntArray = IntArray(0)
	    var chkSums: IntArray = IntArray(0)
		fun run(n: Int = 10): String {
			FannkuchRedux.n = n
	        Fact = IntArray(size = n+1)
	        Fact[0] = 1
	        for (i in (1 until Fact.size)) {
	            Fact[i] = Fact[i-1] * i
	        }
	        CHUNKSZ = (Fact[n] + NCHUNKS - 1) / NCHUNKS
			NTASKS = (Fact[n] + CHUNKSZ - 1) / CHUNKSZ
	        maxFlips = IntArray(size = NTASKS)
	        chkSums  = IntArray(size = NTASKS)
	        FannkuchRedux().run()
	        var res = 0
	        for (v in maxFlips) {
	            res = Math.max(res, v)
	        }
	        var chk = 0
	        for (v in chkSums) {
	            chk += v
	        }
	        return "$chk\nPfannkuchen($n) = $res"
		}
	}
	var p: IntArray = IntArray(0)
	var pp: IntArray = IntArray(0)
	var count: IntArray = IntArray(0)
	fun firstPermutation(idx: Int) {
        for (i in (0 until p.size)) {
           p[i] = i
        }
        var idx = idx
        for (i in (count.size-1 downTo 0)) {
            val d = idx / Fact[i]
            count[i] = d
            idx = idx % Fact[i]
            System.arraycopy(p, 0, pp, 0, i+1)
            for (j in 0..i) {
                p[j] = if (j+d <= i) { pp[j+d] } else { pp[j+d-i-1] }
            }
        }
    }
    fun nextPermutation(): Boolean {
        var first = p[1]
        p[1] = p[0]
        p[0] = first
        var i = 1
        count[i] += 1
        while (count[i] > i) {
            count[i] = 0
            i += 1
            p[0] = p[1]
            val next = p[0]
            for (j in (1 until i)) {
                p[j] = p[j+1]
            }
            p[i] = first
            first = next
            count[i] += 1
        }
        return true
    }
    fun countFlips(): Int {
        var flips = 1
		var first = p[0]
        if (p[first] != 0) {
            System.arraycopy(p, 0, pp, 0, pp.size)
            do {
				flips += 1;
                var lo = 1
                var hi = first - 1
                while (lo < hi) {
                   val t = pp[lo]
                   pp[lo] = pp[hi]
                   pp[hi] = t
                   lo += 1
                   hi -= 1
                }
                val t = pp[first]
                pp[first] = first
                first = t
            } while ( pp[first] != 0 )
        }
		return flips;
    }
    fun runTask(task: Int) {
        val idxMin = task * CHUNKSZ
        val idxMax = Math.min(Fact[n], idxMin+CHUNKSZ)
		firstPermutation(idxMin)
        var maxflips = 1
        var chksum = 0
        var i = idxMin
		while (true) {
            if (p[0] != 0) {
                val flips = countFlips()
                maxflips = Math.max(maxflips, flips)
				chksum += if (i%2 ==0) { flips } else { -flips }
            }
            i += 1
	    	if (i == idxMax) {
	        	break
	    	}
            nextPermutation()
        }
		maxFlips[task] = maxflips
		chkSums[task]  = chksum
    }
    fun run() {
        p = IntArray(size = n)
        pp = IntArray(size = n)
        count = IntArray(size = n)
        for (task in (0 until NTASKS)) {
	    	runTask(task);
        }
    }
}
