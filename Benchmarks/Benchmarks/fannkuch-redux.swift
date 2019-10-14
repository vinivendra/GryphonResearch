private struct Fannkuch {
    static var fact: [Int] = []
    static var chunksz = 0
    static var maxFlips: [Int] = []
    static var chkSums: [Int] = []
    let n: Int
    var p: [Int]
    var pp: [Int]
    var count: [Int]
    init(n: Int) {
        self.n = n
        p = [Int](repeating: 0, count: n)
        pp = [Int](repeating: 0, count: n)
        count = [Int](repeating: 0, count: n)
    }
    mutating func firstPermutation(_ idx: Int) {
        for i in 0..<n {
            p[i] = i
        }
        let start = n - 1
        var indx = idx
        for i in stride(from: start, to: 0, by: -1) {
            let d = indx / Fannkuch.fact[i]
            count[i] = d
            indx = indx % Fannkuch.fact[i]
            pp.replaceSubrange(0..<(i+1), with: p)
            for j in 0...i {
                p[j] = j+d <= i ? pp[j+d] : pp[j+d-i-1]
            }
        }
    }
    mutating func nextPermutation() -> Bool {
        var first = p[1]
        p[1] = p[0]
        p[0] = first
        var i = 1
        count[i] += 1
        while count[i] > i {
            count[i] = 0
            i += 1
            p[0] = p[1]
            let next = p[0]
            for j in 1..<i {
                p[j] = p[j+1]
            }
            p[i] = first
            first = next
            count[i] += 1
        }
        return true
    }
    mutating func countFlips() -> Int {
        var flips = 1
        var first = p[0]
        if p[first] != 0 {
            pp.replaceSubrange(0..<n, with: p)
            repeat {
                flips += 1
                var lo = 1
                var hi = first - 1
                while lo < hi {
                    let t = pp[lo]
                    pp[lo] = pp[hi]
                    pp[hi] = t
                    lo += 1
                    hi -= 1
                }
                let t = pp[first]
                pp[first] = first
                first = t
            } while pp[first] != 0
        }
        return flips
    }
    mutating func runTask(_ task: Int) {
        let idxMin = task * Fannkuch.chunksz
        let idxMax = (Fannkuch.fact[n] < idxMin + Fannkuch.chunksz) ?
            Fannkuch.fact[n] :
            idxMin + Fannkuch.chunksz
        firstPermutation(idxMin)
        var maxflips = 1
        var chksum = 0
        var i = idxMin
        while true {
            if p[0] != 0 {
                let flips = countFlips()
                if flips > maxflips {
                    maxflips = flips
                }
                chksum += (i%2 == 0) ? flips : -flips
            }
            i += 1
            if i == idxMax {
                break
            }
            _ = nextPermutation()
        }
        Fannkuch.maxFlips[task] = maxflips
        Fannkuch.chkSums[task] = chksum
    }
}
enum FannkuchRedux {
    static func run(n: Int = 10) -> String {
        Fannkuch.fact = [Int].init(repeating: 1, count: n + 1)
        for i in 1...n {
            Fannkuch.fact[i] = Fannkuch.fact[i-1] * i
        }
        let nchunks = 150
        Fannkuch.chunksz = (Fannkuch.fact[n] + nchunks - 1) / nchunks
        let ntasks = (Fannkuch.fact[n] + Fannkuch.chunksz - 1) / Fannkuch.chunksz
        Fannkuch.maxFlips = [Int](repeating: 0, count: ntasks)
        Fannkuch.chkSums = [Int](repeating: 0, count: ntasks)
        for i in 0..<ntasks {
            var fannkuch = Fannkuch(n: n)
            fannkuch.runTask(i)
        }
        var res = 0
        for flips in Fannkuch.maxFlips {
            if flips > res {
                res = flips
            }
        }
        var chksum = 0
        for chk in Fannkuch.chkSums {
            chksum += chk
        }
        return "\(chksum)\nPfannkuchen(\(n)) = \(res)"
    }
}
