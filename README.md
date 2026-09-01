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

All apps import the BOM and pick modules without versions. Release APKs, unsigned, R8 enabled (deltas vs the smallest app, `app-lib1`):

| App | Libraries | APK size | Δ vs lib-1 |
|---|---|---|---|
| `app-lib1` | lib-1 | 766,493 B (0.73 MB) | — |
| `app-lib3` | lib-3 | 766,493 B (0.73 MB) | +0 B |
| `app-lib13` | lib-1 + lib-3 | 782,881 B (0.74 MB) | +16 KB |
| `app-lib4` | lib-4 | 799,261 B (0.76 MB) | +32 KB |
| `app-lib34` | lib-3 + lib-4 | 799,265 B (0.76 MB) | +32 KB |
| `app-lib2` | lib-2 | 815,645 B (0.77 MB) | +48 KB |
| `app-lib12` | lib-1 + lib-2 | 815,649 B (0.77 MB) | +48 KB |
| `app-lib123` | lib-1 + lib-2 + lib-3 | 832,033 B (0.79 MB) | +64 KB |
| `app-lib1234` | lib-1..4 individually | 881,185 B (0.84 MB) | +112 KB |
| `app` | lib-full (all four) | 954,091 B (0.90 MB) | +183 KB |

Without R8, every one of these APKs is byte-for-byte identical (7,887,436 B) — the shared transitive Compose stack dwarfs the widgets.

Takeaways:

- **Size is driven by which Compose code paths R8 must keep, not by module count**: lib-2 (Material buttons) costs ~48 KB, lib-4 (clickable + saveable state) ~32 KB, while lib-1 and lib-3 (passive card / canvas animation) add nothing beyond the shared baseline.
- **Combinations are almost free**: adding lib-1 to lib-2 (`app-lib12`) costs 4 bytes over lib-2 alone — the marginal cost of a lib is only whatever code paths it uniquely uses.
- **`app-lib1234` (four individual libs) == the minimal lib-full app == 881,185 B**: depending on lib-full or on all four modules yields identical bits, so granular modules only save size when you *don't* need everything.
- `app` is bigger than `app-lib1234` only because it also ships launcher icons, a custom Material theme, and edge-to-edge setup (~70 KB).
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
