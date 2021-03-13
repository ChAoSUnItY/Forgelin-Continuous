# Forgelin
[![](https://jitpack.io/v/ChAoSUnItY/Forgelin-Continuous.svg)](https://jitpack.io/#ChAoSUnItY/Forgelin-Continuous)  
Fork of [Shadowfacts's Forgelin](https://github.com/shadowfacts/Forgelin)
## Addition
This mod shades:
- Reflect
- Standard Library
- Runtime Library
- Coroutine
- Serialization
  - Json Only
## Usages
```groovy
repositories {
  maven {
    url "https://www.cursemaven.com"
    content {
      includeGroup "curse.maven"
    }
  }
}

dependencies {
  compile "curse.maven:forgelin-continuous-456403:LATEST_FILE_ID"
}
```

Latest file id can be seen [here](https://www.curseforge.com/minecraft/mc-mods/forgelin-continuous/files).

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