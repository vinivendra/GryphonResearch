data class Fannkuch(
    val n: Int,
    var p: IntArray,
    var pp: IntArray,
    var count: IntArray
) {
    companion object {
        var fact: IntArray = IntArray(size = 0)
        var chunksz: Int = 0
        var maxFlips: IntArray = IntArray(size = 0)
        var chkSums: IntArray = IntArray(size = 0)
    }
    constructor(n: Int): this(
        n = n,
        p = IntArray(size = n) { 0 },
        pp = IntArray(size = n) { 0 },
        count = IntArray(size = n) { 0 }
        ) { }
    internal fun firstPermutation(idx: Int) {
        for (i in 0 until n) {
            p[i] = i
        }
        val start: Int = n - 1
        var indx: Int = idx
        var i: Int = start
        while (i > 0) {
            val d: Int = indx / Fannkuch.fact[i]
            count[i] = d
            indx = indx % Fannkuch.fact[i]
            System.arraycopy(p, 0, pp, 0, i+1)
            for (j in 0..i) {
                p[j] = if (j + d <= i) { pp[(j + d)] } else { pp[(j + d - i - 1)] }
            }
            i -= 1
        }
    }
    internal fun nextPermutation(): Boolean {
        var first: Int = p[1]
        p[1] = p[0]
        p[0] = first
        var i: Int = 1
        count[i] += 1
        while (count[i] > i) {
            count[i] = 0
            i += 1
            p[0] = p[1]
            val next: Int = p[0]
            for (j in 1 until i) {
                p[j] = p[j + 1]
            }
            p[i] = first
            first = next
            count[i] += 1
        }
        return true
    }
    internal fun countFlips(): Int {
        var flips: Int = 1
        var first: Int = p[0]
        if (p[first] != 0) {
            System.arraycopy(p, 0, pp, 0, pp.size)
            while (true) {
                flips += 1
                var lo: Int = 1
                var hi: Int = first - 1
                while (lo < hi) {
                    val t: Int = pp[lo]
                    pp[lo] = pp[hi]
                    pp[hi] = t
                    lo += 1
                    hi -= 1
                }
                val t: Int = pp[first]
                pp[first] = first
                first = t
                if (pp[first] == 0) {
                    break
                }
            }
        }
        return flips
    }
    internal fun runTask(task: Int) {
        val idxMin: Int = task * Fannkuch.chunksz
        val idxMax: Int = if (Fannkuch.fact[(n)] < idxMin + Fannkuch.chunksz) { Fannkuch.fact[(n)] } else { idxMin + Fannkuch.chunksz }
        firstPermutation(idxMin)
        var maxflips: Int = 1
        var chksum: Int = 0
        var i: Int = idxMin
        while (true) {
            if (p[0] != 0) {
                val flips: Int = countFlips()
                if (flips > maxflips) {
                    maxflips = flips
                }
                chksum += if (i % 2 == 0) { flips } else { -flips }
            }
            i += 1
            if (i == idxMax) {
                break
            }
            nextPermutation()
        }
        Fannkuch.maxFlips[task] = maxflips
        Fannkuch.chkSums[task] = chksum
    }
}
internal enum class GryphonFannkuchRedux {
;
    companion object {
        internal fun run(n: Int = 10): String {
            Fannkuch.fact = IntArray(size = n + 1) { 1 }
            for (i in 1..n) {
                Fannkuch.fact[i] = Fannkuch.fact[i - 1] * i
            }
            val nchunks: Int = 150
            Fannkuch.chunksz = (Fannkuch.fact[n] + nchunks - 1) / nchunks
            val ntasks: Int = (Fannkuch.fact[n] + Fannkuch.chunksz - 1) / Fannkuch.chunksz
            Fannkuch.maxFlips = IntArray(size = ntasks) { 0 }
            Fannkuch.chkSums = IntArray(size = ntasks) { 0 }
            for (i in 0 until ntasks) {
                var fannkuch: Fannkuch = Fannkuch(n = n)
                fannkuch.runTask(i)
            }
            var res: Int = 0
            for (flips in Fannkuch.maxFlips) {
                if (flips > res) {
                    res = flips
                }
            }
            var chksum: Int = 0
            for (chk in Fannkuch.chkSums) {
                chksum += chk
            }
            return "${chksum}\nPfannkuchen(${n}) = ${res}"
        }
    }
}
