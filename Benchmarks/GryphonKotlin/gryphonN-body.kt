open class GryphonNBody {
    companion object {
        internal fun run(n: Int = 1000): String {
            val bodies: GryphonNBody = GryphonNBody()
            var result: String = "${bodies.energy()}\n"
            for (_0 in 0 until n) {
                bodies.advance(dt = 0.01)
            }
            result += "${bodies.energy()}\n"
            return result
        }
        val solarMass: Double = 4.0 * Math.PI * Math.PI
        val daysPerYear: Double = 365.24
        val bodySize: Int = 8
        val bodyCount: Int = 5
        val x: Int = 0
        val y: Int = 1
        val z: Int = 2
        val vx: Int = 3
        val vy: Int = 4
        val vz: Int = 5
        val mass: Int = 6
    }
    var bodies: DoubleArray = doubleArrayOf(
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        GryphonNBody.solarMass,
        0.0,
        4.841431442464721,
        -1.16032004402742839e+00,
        -1.03622044471123109e-01,
        0.001660076642744037 * GryphonNBody.daysPerYear,
        0.007699011184197404 * GryphonNBody.daysPerYear,
        -6.90460016972063023e-05 * daysPerYear,
        0.0009547919384243266 * GryphonNBody.solarMass,
        0.0,
        8.34336671824458,
        4.124798564124305,
        -4.03523417114321381e-01,
        -2.76742510726862411e-03 * daysPerYear,
        0.004998528012349172 * GryphonNBody.daysPerYear,
        2.3041729757376393e-05 * GryphonNBody.daysPerYear,
        0.0002858859806661308 * GryphonNBody.solarMass,
        0.0,
        12.894369562139131,
        -1.51111514016986312e+01,
        -2.23307578892655734e-01,
        0.002964601375647616 * GryphonNBody.daysPerYear,
        0.0023784717395948095 * GryphonNBody.daysPerYear,
        -2.96589568540237556e-05 * daysPerYear,
        4.366244043351563e-05 * GryphonNBody.solarMass,
        0.0,
        15.379697114850917,
        -2.59193146099879641e+01,
        0.17925877295037118,
        0.0026806777249038932 * GryphonNBody.daysPerYear,
        0.001628241700382423 * GryphonNBody.daysPerYear,
        -9.51592254519715870e-05 * daysPerYear,
        5.1513890204661145e-05 * GryphonNBody.solarMass,
        0.0)
    constructor() {
        var px: Double = 0.0
        var py: Double = 0.0
        var pz: Double = 0.0
        for (i in 0 until GryphonNBody.bodyCount) {
            val ioffset: Int = GryphonNBody.bodySize * i
            val imass: Double = bodies[ioffset + GryphonNBody.mass]
            px += bodies[ioffset + GryphonNBody.vx] * imass
            py += bodies[ioffset + GryphonNBody.vy] * imass
            pz += bodies[ioffset + GryphonNBody.vz] * imass
        }
        bodies[GryphonNBody.vx] = -px / GryphonNBody.solarMass
        bodies[GryphonNBody.vy] = -py / GryphonNBody.solarMass
        bodies[GryphonNBody.vz] = -pz / GryphonNBody.solarMass
    }
    private fun advance(dt: Double) {
        for (i in 0 until GryphonNBody.bodyCount) {
            val offset: Int = GryphonNBody.bodySize * i
            var j: Int = i + 1
            while (j < GryphonNBody.bodyCount) {
                val ioffset: Int = offset
                val joffset: Int = GryphonNBody.bodySize * j
                val dx: Double = bodies[ioffset + GryphonNBody.x] - bodies[joffset + GryphonNBody.x]
                val dy: Double = bodies[ioffset + GryphonNBody.y] - bodies[joffset + GryphonNBody.y]
                val dz: Double = bodies[ioffset + GryphonNBody.z] - bodies[joffset + GryphonNBody.z]
                val dSquared: Double = dx * dx + dy * dy + dz * dz
                val distance: Double = Math.sqrt(dSquared)
                val mag: Double = dt / (dSquared * distance)
                val jmass: Double = bodies[joffset + GryphonNBody.mass]
                bodies[ioffset + GryphonNBody.vx] -= dx * jmass * mag
                bodies[ioffset + GryphonNBody.vy] -= dy * jmass * mag
                bodies[ioffset + GryphonNBody.vz] -= dz * jmass * mag
                val imass: Double = bodies[ioffset + GryphonNBody.mass]
                bodies[joffset + GryphonNBody.vx] += dx * imass * mag
                bodies[joffset + GryphonNBody.vy] += dy * imass * mag
                bodies[joffset + GryphonNBody.vz] += dz * imass * mag
                j += 1
            }
        }
        for (i in 0 until GryphonNBody.bodyCount) {
            val ioffset: Int = GryphonNBody.bodySize * i
            bodies[ioffset + GryphonNBody.x] += dt * bodies[ioffset + GryphonNBody.vx]
            bodies[ioffset + GryphonNBody.y] += dt * bodies[ioffset + GryphonNBody.vy]
            bodies[ioffset + GryphonNBody.z] += dt * bodies[ioffset + GryphonNBody.vz]
        }
    }
    private fun energy(): Double {
        var dx: Double = 0.0
        var dy: Double = 0.0
        var dz: Double = 0.0
        var distance: Double = 0.0
        var e: Double = 0.0
        for (i in 0 until GryphonNBody.bodyCount) {
            val offset: Int = GryphonNBody.bodySize * i
            val ivx: Double = bodies[offset + GryphonNBody.vx]
            val ivy: Double = bodies[offset + GryphonNBody.vy]
            val ivz: Double = bodies[offset + GryphonNBody.vz]
            val imass: Double = bodies[offset + GryphonNBody.mass]
            e += 0.5 * imass * (ivx * ivx + ivy * ivy + ivz * ivz)
            var j: Int = i + 1
            while (j < GryphonNBody.bodyCount) {
                val ioffset: Int = offset
                val joffset: Int = GryphonNBody.bodySize * j
                val ix: Double = bodies[ioffset + GryphonNBody.x]
                val iy: Double = bodies[ioffset + GryphonNBody.y]
                val iz: Double = bodies[ioffset + GryphonNBody.z]
                dx = ix - bodies[joffset + GryphonNBody.x]
                dy = iy - bodies[joffset + GryphonNBody.y]
                dz = iz - bodies[joffset + GryphonNBody.z]
                distance = Math.sqrt(dx * dx + dy * dy + dz * dz)
                e -= (imass * bodies[joffset + GryphonNBody.mass]) / distance
                j += 1
            }
        }
        return e
    }
}
