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
| `lib-bom` | — (no code) | Bill of Materials: pins the versions of all modules above |

The main `app` module is the lib-full demo: it renders `FullShowcase` and consumes `lib-full` **from JitPack**, same as the per-library demo apps. All screens and widgets have `@Preview` composables.

## APK size comparison

Five apps each integrate exactly one library **from JitPack** (`com.github.Ssehgal29.librarybompoc:<module>:1.0.0`) and render its widget. Release APKs, unsigned, R8 enabled:

| App | Library | APK (R8 enabled) | APK (no R8) |
|---|---|---|---|
| `app-lib1` | lib-1 (GreetingCard) | 766,493 B (0.73 MB) | 7,887,436 B (7.5 MB) |
| `app-lib2` | lib-2 (CounterChip) | 815,645 B (0.77 MB) | 7,887,436 B (7.5 MB) |
| `app-lib3` | lib-3 (PulseLoader) | 766,493 B (0.73 MB) | 7,887,436 B (7.5 MB) |
| `app-lib4` | lib-4 (RatingStars) | 799,261 B (0.76 MB) | 7,887,436 B (7.5 MB) |
| `app` | lib-full (all four) | 954,091 B (0.90 MB) | 7,887,436 B (7.5 MB) |

Takeaways:

- **Without R8/minification the APKs are byte-for-byte identical** — every lib pulls the same transitive Compose stack (material3/ui/foundation), which dwarfs the few-KB widgets.
- **With R8 the size tracks actual usage**: lib-full costs more than a single lib, since it keeps all four widgets and their code paths (buttons, animation, clickable, etc.).
- `app` is not a perfectly minimal baseline like `app-lib*`: it also ships launcher icons, a custom Material theme, and edge-to-edge setup (~70 KB of the difference). A minimal lib-full app measured 881,185 B (0.84 MB).
- Reproduce with `./gradlew :app:assembleRelease :app-lib1:assembleRelease ...` and check `*/build/outputs/apk/release/`.

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

Then, Firebase-style: import the BOM once and pick modules without versions —

```kotlin
dependencies {
    implementation(platform("com.github.Ssehgal29.librarybompoc:lib-bom:<tag>"))

    // pick only what you need — versions come from the BOM
    implementation("com.github.Ssehgal29.librarybompoc:lib-1")
    implementation("com.github.Ssehgal29.librarybompoc:lib-3")
}
```

…or everything at once:

```kotlin
dependencies {
    implementation(platform("com.github.Ssehgal29.librarybompoc:lib-bom:<tag>"))
    implementation("com.github.Ssehgal29.librarybompoc:lib-full")
}
```

Explicit versions still work without the BOM (`implementation("com.github.Ssehgal29.librarybompoc:lib-1:<tag>")`), but the BOM keeps all modules in lockstep with a single version declaration — the same pattern Firebase uses (`firebase-bom` + versionless `firebase-analytics`, `firebase-auth`, …).

`<tag>` is a git tag (e.g. `1.0.0`), a branch snapshot (`main-SNAPSHOT`), or a commit hash.

## Publishing a release

```bash
git tag 1.0.0
git push origin 1.0.0
```

Then visit https://jitpack.io/#Ssehgal29/librarybompoc and request a build for the tag.
