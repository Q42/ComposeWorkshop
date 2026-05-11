# Module Dependency Graph

## Current Architecture (After Phase 1)

```
┌─────────────────────────────────────────────────────────┐
│                   Android & iOS Apps                    │
├─────────────────────────────────────────────────────────┤
│  androidApp/ (Android)       iosApp/ (iOS)              │
│  └─ MainActivity             └─ IosApp.kt               │
│     depends on: composeApp      depends on: composeApp  │
└──────┬──────────────────────────────────┬───────────────┘
       │                                  │
       └──────────────────┬───────────────┘
                          │
        ┌─────────────────▼────────────────┐
        │     composeApp/ (Shared)         │
        │  Kotlin Multiplatform Library    │
        │  Targets: Android, iOS ARM64,    │
        │           iOS Simulator          │
        │                                  │
        │  Contains:                       │
        │  - commonMain/                   │
        │    └─ theme/                     │
        │       ├─ Color.kt (shared)       │
        │       └─ Theme.kt (shared)       │
        │                                  │
        │  depends on: shared              │
        └──────────────┬────────────────────┘
                       │
        ┌──────────────▼────────────────┐
        │     shared/ (Shared)          │
        │  Kotlin Multiplatform Library │
        │  Targets: Android, iOS        │
        │                               │
        │  (Currently empty, will hold  │
        │   domain/data models)         │
        └───────────────────────────────┘
```

## Module Details

### `androidApp/` (Android Application)
- **Type:** Android Application Module
- **Targets:** Android only
- **Entry Point:** `MainActivity` in `nl.q42.instagram.ui`
- **Dependencies:**
  - `:composeApp` (new, provides shared UI)
  - AndroidX Core, Lifecycle, Compose, Material3
  - Lottie animations
- **Responsibility:**
  - Android app launch and lifecycle
  - Android-specific resource access (R.drawable.*, etc.)
  - Android testing

### `composeApp/` (Shared UI Layer) — **NEW**
- **Type:** Kotlin Multiplatform Library with Compose Multiplatform
- **Targets:** `androidTarget()`, `iosArm64()`, `iosSimulatorArm64()`
- **Entry Points:**
  - Android: Used via `:androidApp` module
  - iOS: Exposed for iOS app integration
- **Contains:**
  - `commonMain/` — Shared Compose UI (future: Home, FeedItem, etc.)
  - `commonMain/kotlin/nl/q42/instagram/theme/` — Shared Material3 theme
  - `androidMain/` — Android-specific Compose tweaks (if needed)
  - `iosMain/` — iOS-specific Compose tweaks (if needed)
- **Plugins:** `kotlin-multiplatform`, `android-library`, `compose-multiplatform`, `kotlin-compose`
- **Dependencies:**
  - `:shared` (will depend on domain models once created)
  - Compose Multiplatform runtime, UI, Material3
  - Platform-specific: Lottie (Android only for now)

### `shared/` (Shared Domain/Data) — **NEW**
- **Type:** Kotlin Multiplatform Library
- **Targets:** `androidTarget()`, `iosArm64()`, `iosSimulatorArm64()`
- **Contains:** (Currently empty, prepared for future use)
  - ViewState models
  - ViewModels (if using MVVM)
  - Data repositories
  - Network clients
- **Plugins:** `kotlin-multiplatform`, `android-library`, `compose-multiplatform`, `kotlin-compose`
- **Dependencies:** Minimal (Compose runtime for state management)

### `iosApp/` (iOS Integration) — **NEW**
- **Type:** Kotlin Multiplatform Library (iOS targets only)
- **Targets:** `iosArm64()`, `iosSimulatorArm64()`
- **Contains:**
  - `iosMain/kotlin/nl/q42/instagram/ios/IosApp.kt` — iOS-specific Compose entry stub
- **Plugins:** `kotlin-multiplatform`
- **Dependencies:**
  - `:composeApp` (provides shared UI)
- **Responsibility:**
  - iOS-specific integration (safe area handling, lifecycle)
  - iOS app launch preparation

---

## Dependency Constraints & Best Practices

### ✅ Correct Dependencies
```
androidApp → composeApp    ✅ OK (Android app uses shared UI)
androidApp → shared        ✅ OK (Android app uses shared models)
iosApp → composeApp ✅ OK (iOS uses shared UI)
composeApp → shared ✅ OK (shared UI may use shared models)
shared → nothing    ✅ OK (bottom layer, no app dependencies)
```

### ❌ Forbidden Dependencies (to prevent circular imports)
```
composeApp → androidApp ❌ FORBIDDEN (would cause circular dependency)
shared → composeApp     ❌ FORBIDDEN (would cause circular dependency)
shared → androidApp     ❌ FORBIDDEN (would cause circular dependency)
```

---

## Build Artifact Flow

```
gradle build
├─ :androidApp:assemble
│  └─ produces: app-release.apk (Android app)
│     depends on: :composeApp (compiled as AAR)
│
├─ :composeApp:assemble
│  └─ produces: composeApp.aar (shared Compose library)
│     used by: Android app
│     depends on: :shared (compiled as AAR)
│
├─ :composeApp:linkDebugFrameworkIosArm64
│  └─ produces: composeApp.framework (iOS framework)
│     used by: iOS app
│     supports: iPhone device (ARM64)
│
├─ :composeApp:linkDebugFrameworkIosSimulatorArm64
│  └─ produces: composeApp.framework (iOS framework)
│     used by: iOS simulator
│     supports: iOS simulator on Apple Silicon
│
└─ :shared:assemble
   └─ produces: shared.aar / .klib (shared library)
      used by: composeApp, iosApp
```

---

## Platform-Specific Code Management

### Android-Only Code
```
Location: androidApp/src/main/kotlin/
  └─ res/
     ├─ drawable/    (images, icons)
     ├─ values/      (strings, colors, dimens)
     └─ layout/      (XML layouts if any)
  └─ kotlin/
     └─ nl/q42/instagram/ui/
        └─ (Android-specific composables)
```

### Shared Code
```
Location: composeApp/src/commonMain/kotlin/
  └─ nl/q42/instagram/
     ├─ theme/       (Material3 theme)
     ├─ ui/          (Composables used on both platforms)
     └─ viewstate/   (UI state models)
```

### iOS-Only Code
```
Location: iosApp/src/iosMain/kotlin/
  └─ nl/q42/instagram/ios/
     └─ (iOS-specific integration)
```

---

## Next Steps (Phase 2)

**Goal:** Move Home screen to shared code

1. Move `androidApp/src/main/kotlin/nl/q42/instagram/ui/home/Home.kt` → `composeApp/src/commonMain/kotlin/nl/q42/instagram/ui/home/Home.kt`
2. Move `androidApp/src/main/kotlin/nl/q42/instagram/ui/home/FeedItemViewState.kt` → `composeApp/src/commonMain/kotlin/nl/q42/instagram/ui/home/FeedItemViewState.kt`
3. Refactor into `HomeScreen` (stateful) + `HomeContent` (stateless)
4. Remove Home-related imports from `androidApp/src/main/`
5. Verify both Android and iOS render the same Home screen

---

**Last Updated:** 2026-04-17  
**Phase:** 1 (Skeleton Setup) ✅

