
class NBody {
	companion object {
		var n: Int = 0
		fun run(n: Int = 1000): String {
			val bodies = NBodySystem()
			var result = "${bodies.energy()}\n"
			for (i in (0 until n)) {
				bodies.advance(0.01)
			}
			result += "${bodies.energy()}\n"
			return result
		}
	}
}
class NBodySystem {
    companion object {
    	val PI = 3.141592653589793
    	val SOLAR_MASS = 4 * PI * PI
    	val DAYS_PER_YEAR = 365.24
    	val BODY_SIZE = 8
    	val BODY_COUNT = 5
      	val x = 0
      	val y = 1
      	val z = 2
      	val vx = 3
      	val vy = 4
      	val vz = 5
      	val mass = 6
    }
    val bodies: DoubleArray = doubleArrayOf(
	            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, SOLAR_MASS, 0.0,
	            4.84143144246472090e+00,
	            -1.16032004402742839e+00,
	            -1.03622044471123109e-01,
	            1.66007664274403694e-03 * DAYS_PER_YEAR,
	            7.69901118419740425e-03 * DAYS_PER_YEAR,
	            -6.90460016972063023e-05 * DAYS_PER_YEAR,
	            9.54791938424326609e-04 * SOLAR_MASS,
	            0.0,
	            8.34336671824457987e+00,
	            4.12479856412430479e+00,
	            -4.03523417114321381e-01,
	            -2.76742510726862411e-03 * DAYS_PER_YEAR,
	            4.99852801234917238e-03 * DAYS_PER_YEAR,
	            2.30417297573763929e-05 * DAYS_PER_YEAR,
	            2.85885980666130812e-04 * SOLAR_MASS,
	            0.0,
	            1.28943695621391310e+01,
	            -1.51111514016986312e+01,
	            -2.23307578892655734e-01,
	            2.96460137564761618e-03 * DAYS_PER_YEAR,
	            2.37847173959480950e-03 * DAYS_PER_YEAR,
	            -2.96589568540237556e-05 * DAYS_PER_YEAR,
	            4.36624404335156298e-05 * SOLAR_MASS,
	            0.0,
	            1.53796971148509165e+01,
	            -2.59193146099879641e+01,
	            1.79258772950371181e-01,
	            2.68067772490389322e-03 * DAYS_PER_YEAR,
	            1.62824170038242295e-03 * DAYS_PER_YEAR,
	            -9.51592254519715870e-05 * DAYS_PER_YEAR,
	            5.15138902046611451e-05 * SOLAR_MASS, 
	            0.0
            )
    init {
    	var px = 0.0
        var py = 0.0
        var pz = 0.0
        for (i in (0 until BODY_COUNT)) {
            val ioffset = BODY_SIZE * i
            val imass = bodies[ioffset + mass]
            px += bodies[ioffset + vx] * imass
            py += bodies[ioffset + vy] * imass
            pz += bodies[ioffset + vz] * imass
        }
        bodies[vx] = -px / SOLAR_MASS
        bodies[vy] = -py / SOLAR_MASS
        bodies[vz] = -pz / SOLAR_MASS
    }
    fun advance(dt: Double) {
        for (i in (0 until BODY_COUNT)) {
            val offset = BODY_SIZE * i
            var j = i + 1
            while (j < BODY_COUNT) {
				val ioffset = offset
				val joffset = BODY_SIZE * j
				val dx = bodies[ioffset + x] - bodies[joffset + x]
				val dy = bodies[ioffset + y] - bodies[joffset + y]
				val dz = bodies[ioffset + z] - bodies[joffset + z]
				val dSquared = dx * dx + dy * dy + dz * dz
				val distance = Math.sqrt(dSquared)
				val mag = dt / (dSquared * distance)
				val jmass = bodies[joffset + mass]
				bodies[ioffset + vx] -= dx * jmass * mag
				bodies[ioffset + vy] -= dy * jmass * mag
				bodies[ioffset + vz] -= dz * jmass * mag
				val imass = bodies[ioffset + mass]
				bodies[joffset + vx] += dx * imass * mag
				bodies[joffset + vy] += dy * imass * mag
				bodies[joffset + vz] += dz * imass * mag
				j += 1
            }
        }
        for (i in (0 until BODY_COUNT)) {
            val ioffset = BODY_SIZE * i
            bodies[ioffset + x] += dt * bodies[ioffset + vx]
            bodies[ioffset + y] += dt * bodies[ioffset + vy]
            bodies[ioffset + z] += dt * bodies[ioffset + vz]
        }
    }
    fun energy(): Double {
        var dx = 0.0
        var dy = 0.0
        var dz = 0.0
        var distance = 0.0
        var e = 0.0
        for (i in (0 until BODY_COUNT)) {
            val offset = BODY_SIZE * i
            val ivx = bodies[offset + vx]
            val ivy = bodies[offset + vy]
            val ivz = bodies[offset + vz]
            val imass = bodies[offset + mass]
            e += 0.5 * imass * (ivx * ivx + ivy * ivy + ivz * ivz)
            var j = i + 1
            while (j < BODY_COUNT) {
				val ioffset = offset
				val joffset = BODY_SIZE * j
				val ix = bodies[ioffset + x]
				val iy = bodies[ioffset + y]
				val iz = bodies[ioffset + z]
				dx = ix - bodies[joffset + x]
				dy = iy - bodies[joffset + y]
				dz = iz - bodies[joffset + z]
				distance = Math.sqrt(dx * dx + dy * dy + dz * dz)
				e -= (imass * bodies[joffset + mass]) / distance
				j += 1
            }
        }
        return e
    }
}