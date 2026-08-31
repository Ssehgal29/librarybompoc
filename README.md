# library-bom-poc

POC for publishing multiple Android library modules to [JitPack](https://jitpack.io) from a single repo.

[![](https://jitpack.io/v/Ssehgal29/librarybompoc.svg)](https://jitpack.io/#Ssehgal29/librarybompoc)

## Modules

| Module | Widget | Description |
|---|---|---|
| `lib-1` | `GreetingCard` | Blue greeting card |
| `lib-2` | `CounterChip` | Green counter chip with +/- buttons |
| `lib-3` | `PulseLoader` | Orange animated pulsing loader |
| `lib-4` | `RatingStars` | Purple tappable 5-star rating bar |
| `lib-full` | `FullShowcase` | Aggregates all four libs (`api` dependencies) and shows every widget |

The `app` module is a demo that renders `FullShowcase` from `lib-full`.

## Usage

Add JitPack to your repositories (`settings.gradle.kts`):

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

Then depend on individual modules:

```kotlin
dependencies {
    implementation("com.github.Ssehgal29.librarybompoc:lib-1:<tag>")
    implementation("com.github.Ssehgal29.librarybompoc:lib-2:<tag>")
    implementation("com.github.Ssehgal29.librarybompoc:lib-3:<tag>")
    implementation("com.github.Ssehgal29.librarybompoc:lib-4:<tag>")
}
```

…or everything at once:

```kotlin
dependencies {
    implementation("com.github.Ssehgal29.librarybompoc:lib-full:<tag>")
}
```

`<tag>` is a git tag (e.g. `1.0.0`), a branch snapshot (`main-SNAPSHOT`), or a commit hash.

## Publishing a release

```bash
git tag 1.0.0
git push origin 1.0.0
```

Then visit https://jitpack.io/#Ssehgal29/librarybompoc and request a build for the tag.
