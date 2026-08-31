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

The `app` module is a demo that renders `FullShowcase` from `lib-full` (local project dependency).

## APK size comparison

Five demo apps (`app-lib1` … `app-lib4`, `app-full`) each integrate exactly one library **from JitPack** (`com.github.Ssehgal29.librarybompoc:<module>:1.0.0`) and render its widget. Release APKs, unsigned:

| App | Library | APK (R8 enabled) | APK (no R8) |
|---|---|---|---|
| `app-lib1` | lib-1 (GreetingCard) | 766,493 B (0.73 MB) | 7,887,436 B (7.5 MB) |
| `app-lib2` | lib-2 (CounterChip) | 815,645 B (0.77 MB) | 7,887,436 B (7.5 MB) |
| `app-lib3` | lib-3 (PulseLoader) | 766,493 B (0.73 MB) | 7,887,436 B (7.5 MB) |
| `app-lib4` | lib-4 (RatingStars) | 799,261 B (0.76 MB) | 7,887,436 B (7.5 MB) |
| `app-full` | lib-full (all four) | 881,185 B (0.84 MB) | 7,887,436 B (7.5 MB) |

Takeaways:

- **Without R8/minification the APKs are byte-for-byte identical** — every lib pulls the same transitive Compose stack (material3/ui/foundation), which dwarfs the few-KB widgets.
- **With R8 the size tracks actual usage**: lib-full costs ~65–115 KB more than a single lib, since it keeps all four widgets and their code paths (buttons, animation, clickable, etc.).
- Reproduce with `./gradlew :app-lib1:assembleRelease ...` and check `*/build/outputs/apk/release/`.

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
