# Rule Engine For Classifying Celestial Objects

The code base for [SpringBoot: Rule Engine For Classifying Celestial Objects](https://medium.com/@georgeberar.contact/springboot-rule-engine-for-classifying-celestial-objects-6af6d4f824a6) article.

## Getting Started

### The Engine

The Rule Engine is composed of three simple rules:

| Name  | Condition | Precedence |
| :---: | :---: | :---: |
| `Planet`  | The mass should be at most 13 times the mass of Jupiter, which is 1.898 × 10^27 kg  |  1  |
| `Star`  | The mass should be at least 13 times larger than the mass of Jupiter and the surface temperature is at least 2500 Kelvins  |  1  |
| `Black-Hole`  | The physical radius should be smaller than its Schwarzschild radius, where: <br/> 1. physical radius is half of equatorial diameter <br/> 2. Rs = 2GM/c^2 (Rs is the Schwarzschild radius, G is the gravitational constant (6.67 × 10-11 Newtons kg-2 m2), M is the mass of the object and c is the speed of light (299,792,458 metres/second))  |  0  |

The Planet and Star rules are mutually exclusive while Planet vs Black-Hole or Star vs Black-Hole can be satisfied
simultaneously. In such a case the rule with the lower precedence wins.

### Start

Start the application using your favorite dev tool (IntelliJ or Eclipse) or with Maven command ``mvn spring-boot:run``.

> **8080** is used as running port

### Test

``POST http://localhost:8080/api/v1/classifier`` with one of the request bodies:

**1. Classify as PLANET**

```
{
    "name": "Kepler",
    "mass": 5.97237e2,
    "equatorialDiameter": 12756200,
    "surfaceTemperature": 5800
}
```

Result: `PLANET`

**2. Classify as STAR**

```
{
    "name": "Kepler",
    "mass": 3.65e29,
    "equatorialDiameter": 184502000,
    "surfaceTemperature": 4800
}
```

Result: `STAR`

**3. Classify as BLACK-HOLE**

```
{
    "name": "Kepler",
    "mass": 4.2e40,
    "equatorialDiameter": 4280000,
    "surfaceTemperature": 2000
}
```

Result: `BLACK_HOLE`

**4. Classify as BLACK-HOLE based on precedence**

```
{
    "name": "Kepler",
    "mass": 1.898e27,
    "equatorialDiameter": 1,
    "surfaceTemperature": 2000
}
```

Result: `BLACK_HOLE`


