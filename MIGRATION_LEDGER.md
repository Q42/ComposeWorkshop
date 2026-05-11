# Migration Ledger (CMP)

Last updated: 2026-04-17

## Phase Status
- [x] Phase 0 - Feature inventory and risk tags
- [x] Phase 1 - CMP skeleton setup (Android + iOS targets)
- [x] Phase 2 - Shared UI foundation (complete – Batch 2M polish done)
- [x] Phase 3 - Platform boundary cleanup and `Res` migration (complete – Batch 3A done)
- [x] Phase 4 - IDE validation gate (complete – Batch 4A done)

## Feature Parity Ledger

| Feature | Unique behavior to preserve | Current Android implementation | CMP target | Risk | Status |
|---|---|---|---|---|---|
| Home shell | Shows feed inside scaffolded app surface | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `HomeScreen` + `HomeContent` | P0 | ✅ Complete (Batch 2A) |
| Home metadata text | Author/description/post/likes text visible for feed items | `androidApp/ui/data/DummyData.kt` + `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `HomeContent` | P1 | ✅ Complete (Batch 2C–2M) |
| Home metadata structure | Metadata rendering is encapsulated and reusable | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `FeedMetadataItem` | P2 | ✅ Complete (Batch 2D) |
| Home metadata list boundary | Metadata collection rendering is isolated | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `FeedMetadataList` | P2 | ✅ Complete (Batch 2E) |
| Home interaction visibility text | `canLike`/`canFollow` state is visible in shared output | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `FeedMetadataItem` | P1 | ✅ Complete (Batch 2F) |
| Home interaction callback contract | Interaction events are explicitly wired from Screen -> Content | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `HomeInteractionEvent` + `onInteraction` | P1 | ✅ Complete (Batch 2G) |
| Home interaction action result flow | Interaction handling emits typed action results | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `HomeActionResult` + `onActionResult` | P1 | ✅ Complete (Batch 2H) |
| Home status feedback | Interaction handling updates shared state with visible snackbar feedback | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `statusMessage` + reducers | P1 | ✅ Complete (Batch 2I–2M) |
| Home image resource boundary | Shared feed contract no longer stores Android drawable ids | `androidApp/ui/data/DummyData.kt` | `composeApp/commonMain` `FeedImageKey` | P1 | ✅ Complete (Batch 2J + 3A) |
| Home author image | Author images render in shared code via CMP `Res` | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `FeedMetadataItem` + `Res.drawable` | P1 | ✅ Complete (Batch 3A) |
| Home post image | Post images render in shared code via CMP `Res` | `androidApp/ui/home/Home.kt` | `composeApp/commonMain` `FeedMetadataItem` + `Res.drawable` | P1 | ✅ Complete (Batch 3A) |
| Home data contract | `HomeViewState` with feed items | `androidApp/ui/home/Home.kt` and `FeedItemViewState.kt` | `composeApp/commonMain` state models | P1 | ✅ Complete (Batch 2A) |
| Android dummy feed fixtures | Existing animal dataset remains unchanged | `androidApp/ui/data/DummyData.kt` | Android-local `DummyData` feeding shared `FeedItemViewState` | P1 | ✅ Preserved |
| iOS shared screen render path | iOS entrypoint renders shared screen with state | `iosApp/.../IosApp.kt` | Shared `HomeScreen` with stateful reducers | P0 | ✅ Complete (Batch 2A + 3A) |

## P0 Risks (must remain closed)
- [x] Android app compiles after shared Home wiring.
- [x] iOS target compiles after shared Home wiring.
- [x] No behavior removal from current Android visible shell.

## P1/P2 Follow-ups
- [x] Migrate Home feed visuals/interactions from Android-local implementation to shared `HomeContent` incrementally.
- [x] Map interaction state and callbacks in shared `HomeContent`.
- [x] Add previewable stateless content scenarios for shared Home UI.
- [x] Replace Android `R.drawable` leakage from shared contracts (complete in Batch 3A — images migrated to CMP `Res`).
- [x] Delete dead code `HomeFeedImageResources.kt` (done in Batch 4A).

## Batch Evidence

### Batch 4C (iOS uses shared dummy feed)
- What changed:
  - Added shared dummy fixtures in `composeApp/commonMain` (`HomeDummyData.kt`) so both platforms use one feed source.
  - Updated iOS runtime entrypoint `composeApp/src/iosMain/kotlin/nl/q42/instagram/composeapp/MainViewController.kt` to start from `dummyHomeViewState` instead of `emptyHomeViewState`.
  - Updated Android `androidApp/src/main/kotlin/nl/q42/instagram/ui/data/DummyData.kt` to delegate to shared dummy fixtures, preserving existing Android call sites.
- Preserved behavior:
  - Android keeps current feed output; iOS now renders the same dummy feed as Android at startup.
- Why:
  - Close feature parity gap where iOS started with an empty state while Android used populated dummy feed data.
- Risk level: P1 (startup state source change across platforms).
- Validation performed:
  - `:composeApp:compileKotlinIosSimulatorArm64` ✅
  - `:androidApp:assemble` ✅
- Remaining follow-ups:
  - Optional: remove unused `iosApp/src/iosMain/kotlin/nl/q42/instagram/ios/IosApp.kt` runtime wrapper to avoid entrypoint confusion.

### Batch 4B (iOS generated `Info.plist` cleanup)
- What changed:
  - Switched iOS runtime plist ownership fully to Xcode-generated settings in `iosApp/iosApp.xcodeproj/project.pbxproj`.
  - Removed obsolete static plist reference from the Xcode project file.
  - Deleted `iosApp/iosApp/Info.plist` to avoid split source-of-truth configuration.
- Preserved behavior:
  - Shared Compose entrypoint and iOS app wiring remain unchanged.
- Why:
  - Pragmatic fix for Compose UIKit `PlistSanityCheck` crash surface by ensuring required keys are sourced from one consistent place.
- Risk level: P1 (iOS startup configuration path change).
- Validation performed:
  - `:composeApp:compileKotlinIosSimulatorArm64` ✅
- Remaining follow-ups:
  - Reinstall app on simulator/device to clear cached stale bundle metadata after plist strategy switch.

### Batch 4A (Phase 4 IDE validation gate + cleanup)
- What changed:
  - Deleted dead code `androidApp/src/main/kotlin/nl/q42/instagram/ui/data/HomeFeedImageResources.kt` (no longer needed after Batch 3A image migration).
  - Updated feature parity ledger: all "Partial migration" rows marked complete.
- Preserved behavior:
  - All Android and iOS compilation passes; no runtime behavior change.
- Why:
  - Phase 4 checklist: validate compilation gate passes on both platforms, close all open P0 risks, clean up dead code.
- Risk level: P2 (dead code removal + documentation only).
- Validation performed:
  - `:composeApp:compileDebugKotlinAndroid` ✅
  - `:composeApp:compileKotlinIosSimulatorArm64` ✅
  - `:androidApp:assemble` ✅
  - No open P0 parity risks ✅
- Remaining follow-ups:
  - Migration complete for Android + iOS CMP targets.
  - Future: add navigation, additional screens, ViewModel-backed state, or desktop target as needed.
