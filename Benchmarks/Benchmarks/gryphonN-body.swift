import Foundation
typealias DoubleArray = [Double] // kotlin: ignore
private func gryphonTemplates() {
    _ = Double.pi
    _ = "Math.PI"
}
func doubleArrayOf(_ array: Double...) -> DoubleArray { // kotlin: ignore
    return array
}
class GryphonNBody {
    static func run(n: Int = 1000) -> String {
        let bodies = GryphonNBody()
        var result = "\(bodies.energy())\n"
        for _ in 0..<n {
            bodies.advance(dt: 0.01)
        }
        result += "\(bodies.energy())\n"
        return result
    }
    static let solarMass = 4 * Double.pi * Double.pi
    static let daysPerYear = 365.24
    static let bodySize = 8
    static let bodyCount = 5
    static let x = 0
    static let y = 1
    static let z = 2
    static let vx = 3
    static let vy = 4
    static let vz = 5
    static let mass = 6
    var bodies: DoubleArray = doubleArrayOf(
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, solarMass, 0.0,
        4.84143144246472090e+00,
        -1.16032004402742839e+00, // value: -1.16032004402742839e+00
        -1.03622044471123109e-01, // value: -1.03622044471123109e-01
        1.66007664274403694e-03 * daysPerYear,
        7.69901118419740425e-03 * daysPerYear,
        -6.90460016972063023e-05 * daysPerYear, // value: -6.90460016972063023e-05 * daysPerYear
        9.54791938424326609e-04 * solarMass,
        0.0,
        8.34336671824457987e+00,
        4.12479856412430479e+00,
        -4.03523417114321381e-01, // value: -4.03523417114321381e-01
        -2.76742510726862411e-03 * daysPerYear, // value: -2.76742510726862411e-03 * daysPerYear
        4.99852801234917238e-03 * daysPerYear,
        2.30417297573763929e-05 * daysPerYear,
        2.85885980666130812e-04 * solarMass,
        0.0,
        1.28943695621391310e+01,
        -1.51111514016986312e+01, // value: -1.51111514016986312e+01
        -2.23307578892655734e-01, // value: -2.23307578892655734e-01
        2.96460137564761618e-03 * daysPerYear,
        2.37847173959480950e-03 * daysPerYear,
        -2.96589568540237556e-05 * daysPerYear, // value: -2.96589568540237556e-05 * daysPerYear
        4.36624404335156298e-05 * solarMass,
        0.0,
        1.53796971148509165e+01,
        -2.59193146099879641e+01, // value: -2.59193146099879641e+01
        1.79258772950371181e-01,
        2.68067772490389322e-03 * daysPerYear,
        1.62824170038242295e-03 * daysPerYear,
        -9.51592254519715870e-05 * daysPerYear, // value: -9.51592254519715870e-05 * daysPerYear
        5.15138902046611451e-05 * solarMass,
        0.0
    )
    init() {
        var px = 0.0
        var py = 0.0
        var pz = 0.0
        for i in 0..<GryphonNBody.bodyCount {
            let ioffset = GryphonNBody.bodySize * i
            let imass = bodies[ioffset + GryphonNBody.mass]
            px += bodies[ioffset + GryphonNBody.vx] * imass
            py += bodies[ioffset + GryphonNBody.vy] * imass
            pz += bodies[ioffset + GryphonNBody.vz] * imass
        }
        bodies[GryphonNBody.vx] = -px / GryphonNBody.solarMass
        bodies[GryphonNBody.vy] = -py / GryphonNBody.solarMass
        bodies[GryphonNBody.vz] = -pz / GryphonNBody.solarMass
    }
    private func advance(dt: Double) {
        for i in 0..<GryphonNBody.bodyCount {
            let offset = GryphonNBody.bodySize * i
            var j = i + 1
            while j < GryphonNBody.bodyCount {
                let ioffset = offset
                let joffset = GryphonNBody.bodySize * j
                let dx = bodies[ioffset + GryphonNBody.x] - bodies[joffset + GryphonNBody.x]
                let dy = bodies[ioffset + GryphonNBody.y] - bodies[joffset + GryphonNBody.y]
                let dz = bodies[ioffset + GryphonNBody.z] - bodies[joffset + GryphonNBody.z]
                let dSquared = dx * dx + dy * dy + dz * dz
                let distance = sqrt(dSquared)
                let mag = dt / (dSquared * distance)
                let jmass = bodies[joffset + GryphonNBody.mass]
                bodies[ioffset + GryphonNBody.vx] -= dx * jmass * mag
                bodies[ioffset + GryphonNBody.vy] -= dy * jmass * mag
                bodies[ioffset + GryphonNBody.vz] -= dz * jmass * mag
                let imass = bodies[ioffset + GryphonNBody.mass]
                bodies[joffset + GryphonNBody.vx] += dx * imass * mag
                bodies[joffset + GryphonNBody.vy] += dy * imass * mag
                bodies[joffset + GryphonNBody.vz] += dz * imass * mag
                j += 1
            }
        }
        for i in 0..<GryphonNBody.bodyCount {
            let ioffset = GryphonNBody.bodySize * i
            bodies[ioffset + GryphonNBody.x] += dt * bodies[ioffset + GryphonNBody.vx]
            bodies[ioffset + GryphonNBody.y] += dt * bodies[ioffset + GryphonNBody.vy]
            bodies[ioffset + GryphonNBody.z] += dt * bodies[ioffset + GryphonNBody.vz]
        }
    }
    private func energy() -> Double {
        var dx = 0.0
        var dy = 0.0
        var dz = 0.0
        var distance = 0.0
        var e = 0.0
        for i in 0..<GryphonNBody.bodyCount {
            let offset = GryphonNBody.bodySize * i
            let ivx = bodies[offset + GryphonNBody.vx]
            let ivy = bodies[offset + GryphonNBody.vy]
            let ivz = bodies[offset + GryphonNBody.vz]
            let imass = bodies[offset + GryphonNBody.mass]
            e += 0.5 * imass * (ivx * ivx + ivy * ivy + ivz * ivz)
            var j = i + 1
            while j < GryphonNBody.bodyCount {
                let ioffset = offset
                let joffset = GryphonNBody.bodySize * j
                let ix = bodies[ioffset + GryphonNBody.x]
                let iy = bodies[ioffset + GryphonNBody.y]
                let iz = bodies[ioffset + GryphonNBody.z]
                dx = ix - bodies[joffset + GryphonNBody.x]
                dy = iy - bodies[joffset + GryphonNBody.y]
                dz = iz - bodies[joffset + GryphonNBody.z]
                distance = sqrt(dx * dx + dy * dy + dz * dz)
                e -= (imass * bodies[joffset + GryphonNBody.mass]) / distance
                j += 1
            }
        }
        return e
    }
}
