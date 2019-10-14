import Foundation
class NBody {
    static func run(n: Int = 1000) -> String {
        let bodies = NBody()
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
    var bodies: [Double] = [
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, solarMass, 0.0,
        4.84143144246472090e+00,
        -1.16032004402742839e+00,
        -1.03622044471123109e-01,
        1.66007664274403694e-03 * daysPerYear,
        7.69901118419740425e-03 * daysPerYear,
        -6.90460016972063023e-05 * daysPerYear,
        9.54791938424326609e-04 * solarMass,
        0.0,
        8.34336671824457987e+00,
        4.12479856412430479e+00,
        -4.03523417114321381e-01,
        -2.76742510726862411e-03 * daysPerYear,
        4.99852801234917238e-03 * daysPerYear,
        2.30417297573763929e-05 * daysPerYear,
        2.85885980666130812e-04 * solarMass,
        0.0,
        1.28943695621391310e+01,
        -1.51111514016986312e+01,
        -2.23307578892655734e-01,
        2.96460137564761618e-03 * daysPerYear,
        2.37847173959480950e-03 * daysPerYear,
        -2.96589568540237556e-05 * daysPerYear,
        4.36624404335156298e-05 * solarMass,
        0.0,
        1.53796971148509165e+01,
        -2.59193146099879641e+01,
        1.79258772950371181e-01,
        2.68067772490389322e-03 * daysPerYear,
        1.62824170038242295e-03 * daysPerYear,
        -9.51592254519715870e-05 * daysPerYear,
        5.15138902046611451e-05 * solarMass,
        0.0,
    ]
    init() {
        var px = 0.0
        var py = 0.0
        var pz = 0.0
        for i in 0..<NBody.bodyCount {
            let ioffset = NBody.bodySize * i
            let imass = bodies[ioffset + NBody.mass]
            px += bodies[ioffset + NBody.vx] * imass
            py += bodies[ioffset + NBody.vy] * imass
            pz += bodies[ioffset + NBody.vz] * imass
        }
        bodies[NBody.vx] = -px / NBody.solarMass
        bodies[NBody.vy] = -py / NBody.solarMass
        bodies[NBody.vz] = -pz / NBody.solarMass
    }
    private func advance(dt: Double) {
        for i in 0..<NBody.bodyCount {
            let offset = NBody.bodySize * i
            var j = i + 1
            while j < NBody.bodyCount {
                let ioffset = offset
                let joffset = NBody.bodySize * j
                let dx = bodies[ioffset + NBody.x] - bodies[joffset + NBody.x]
                let dy = bodies[ioffset + NBody.y] - bodies[joffset + NBody.y]
                let dz = bodies[ioffset + NBody.z] - bodies[joffset + NBody.z]
                let dSquared = dx * dx + dy * dy + dz * dz
                let distance = sqrt(dSquared)
                let mag = dt / (dSquared * distance)
                let jmass = bodies[joffset + NBody.mass]
                bodies[ioffset + NBody.vx] -= dx * jmass * mag
                bodies[ioffset + NBody.vy] -= dy * jmass * mag
                bodies[ioffset + NBody.vz] -= dz * jmass * mag
                let imass = bodies[ioffset + NBody.mass]
                bodies[joffset + NBody.vx] += dx * imass * mag
                bodies[joffset + NBody.vy] += dy * imass * mag
                bodies[joffset + NBody.vz] += dz * imass * mag
                j += 1
            }
        }
        for i in 0..<NBody.bodyCount {
            let ioffset = NBody.bodySize * i
            bodies[ioffset + NBody.x] += dt * bodies[ioffset + NBody.vx]
            bodies[ioffset + NBody.y] += dt * bodies[ioffset + NBody.vy]
            bodies[ioffset + NBody.z] += dt * bodies[ioffset + NBody.vz]
        }
    }
    private func energy() -> Double {
        var dx = 0.0
        var dy = 0.0
        var dz = 0.0
        var distance = 0.0
        var e = 0.0
        for i in 0..<NBody.bodyCount {
            let offset = NBody.bodySize * i
            let ivx = bodies[offset + NBody.vx]
            let ivy = bodies[offset + NBody.vy]
            let ivz = bodies[offset + NBody.vz]
            let imass = bodies[offset + NBody.mass]
            e += 0.5 * imass * (ivx * ivx + ivy * ivy + ivz * ivz)
            var j = i + 1
            while j < NBody.bodyCount {
                let ioffset = offset
                let joffset = NBody.bodySize * j
                let ix = bodies[ioffset + NBody.x]
                let iy = bodies[ioffset + NBody.y]
                let iz = bodies[ioffset + NBody.z]
                dx = ix - bodies[joffset + NBody.x]
                dy = iy - bodies[joffset + NBody.y]
                dz = iz - bodies[joffset + NBody.z]
                distance = sqrt(dx * dx + dy * dy + dz * dz)
                e -= (imass * bodies[joffset + NBody.mass]) / distance
                j += 1
            }
        }
        return e
    }
}
