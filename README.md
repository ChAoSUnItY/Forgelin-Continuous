# Forgelin Continuous
[![](https://repo.cleanroommc.com/api/badge/latest/releases/io/github/chaosunity/forgelin/Forgelin-Continuous?color=40c14a&name=Forgelin-Continuous)](https://repo.cleanroommc.com/#/releases/io/github/chaosunity/forgelin/Forgelin-Continuous)  

Fork of [Shadowfacts's Forgelin](https://github.com/shadowfacts/Forgelin)
## Addition
This mod shades:
- Reflect
- Standard Library
- Runtime Library
- Coroutine
- Serialization
  - Json Only

The list of version of bundled libraries is listed in [GitHub Release](https://github.com/ChAoSUnItY/Forgelin-Continuous/releases).

## Usages
### For Gradle Groovy:
```groovy
repositories {
  repositories {
    maven {
      url 'https://maven.cleanroommc.com'
    }
  }
}

dependencies {
  implementation 'io.github.chaosunity.forgelin:Forgelin-Continuous:2.0.0.0'
}
```
### For Gradle Kotlin
```kts
repositories {
    maven {
        url = uri("https://maven.cleanroommc.com")
    }
}

dependencies {
  implementation("io.github.chaosunity.forgelin:Forgelin-Continuous:2.0.0.0")
}
```

Add this line to your mod annotation then you're done with basic settings!

```kotlin
@Mod(
  modid = "modid",
  name = "Mod Name",
  version = "Mod version",
  modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object ExampleMod {
    // ...
}
```
