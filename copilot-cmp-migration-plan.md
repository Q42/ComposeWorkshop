# Copilot CMP Migration Plan (Android Compose -> Android + iOS)

## Purpose
This plan is for a **GitHub Copilot agent only**.

Use it to migrate a **basic Android Compose app that is not yet CMP** to Compose Multiplatform with **Android + iOS targets**.

Primary objective: **preserve the app's unique features and UX behavior** while introducing CMP incrementally.

## Scope and Constraints
- Audience: Copilot agent only.
- Document location: project root.
- App profile: UI-first app, no domain/data layering requirements in this plan.
- Validation mode: IDE-only run configurations (Android Studio/IntelliJ/Xcode), no CI tasks.
- Migration strategy: parity first, then cleanup; small, reversible change batches.

## Preservation-First Rules
- Feature parity is the top priority; do not simplify away unique interactions.
- Keep user-visible behavior stable unless explicitly approved.
- Migrate one feature flow at a time (entry -> interaction -> exit).
- Add lightweight feature parity notes for every migrated screen.
- Prefer adapters/wrappers over rewrites when behavior is uncertain.

## UI and CMP Best Practices to Apply
- Keep composables small and focused.
- Use `Screen`/`Content` split:
  - `*Screen.kt`: stateful, wires ViewModel/lifecycle/navigation.
  - `*Content.kt`: stateless, previewable, receives state + callbacks.
- Keep unidirectional data flow: state up, events down.
- Use sealed `ViewState` per screen (`Loading`, `Content`, `Error`) where applicable.
- Use typed navigation (`Destination` sealed type) over raw route strings.
- Use result-based flow handling (`ActionResult` / `ApiResult`) for async UI actions.
- Isolate platform-specific behavior with `expect/actual` or platform adapters.
- Use shared resources through generated `Res` accessors.
- Use version catalog aliases (`libs.versions.toml`) for dependency/version consistency.
- Avoid Android-only constants/config leakage into shared `commonMain`.

## IDE Run Configuration Expectations (Template-Like)
Define IDE run configurations similar in spirit to this template:

1. Android app run
- Run on emulator/device from Android Studio.
- Used for baseline parity verification against legacy behavior.

2. iOS app run
- Run from Xcode or KMP iOS IDE configuration.
- Used to verify shared UI and iOS-specific integration.

3. JVM desktop hot-reload run (optional but recommended for migration speed)
- Add a desktop dev target only if helpful for rapid UI iteration.
- Use a hot-reload style config (for example: `hotRunJvm --autoReload`).
- This is a dev acceleration target, not a required product platform.

## Migration Execution Phases

### Phase 0 - Feature Inventory and Parity Ledger
- [ ] Create a root migration ledger with: feature, unique behavior, current Android implementation, CMP target, risk.
- [ ] Record all "must-preserve" UX details (animations, gestures, edge cases, timing, copy, visual states).
- [ ] Tag risks:
  - `P0` feature loss or app crash risk
  - `P1` behavior drift risk
  - `P2` maintainability/style cleanup
- [ ] Freeze acceptance snapshots (screenshots/video notes) for key flows.

### Phase 1 - CMP Skeleton Setup (Android + iOS)
- [ ] Add/verify CMP targets for Android and iOS.
- [ ] Create shared UI entry module (`composeApp` or equivalent) and platform entrypoints.
- [ ] Keep Android app launchable at all times during migration.
- [ ] Add iOS launch path and confirm first shared screen renders.

### Phase 2 - Shared UI Foundation
- [ ] Move reusable Compose UI to `commonMain` incrementally.
- [ ] Keep platform-specific UI details behind adapters when needed.
- [ ] Introduce `Screen`/`Content` structure for migrated screens.
- [ ] Ensure migrated content is previewable and state-driven.

### Phase 3 - Platform Boundary Cleanup
- [ ] Remove Android-only APIs from shared code.
- [ ] Introduce `expect/actual` for platform services (for example, share sheet, haptics, permissions wrappers).
- [ ] Verify shared resources use `Res` accessors.
- [ ] Align dependencies with version catalog aliases.

### Phase 4 - IDE Validation Gate
- [ ] Android run config: critical user journeys pass.
- [ ] iOS run config: critical user journeys pass.
- [ ] Optional desktop hot-reload config works (if added).
- [ ] JVM tests pass (if configured).
- [ ] Ledger has no open `P0` parity risks.

## Risk-Based Priority Order
1. Loss of unique feature behavior during migration.
2. Startup/runtime failures on either platform.
3. Navigation/state regressions in critical journeys.
4. Platform leakage into shared code that blocks iOS progress.
5. Cleanup and consistency improvements.

## Acceptance Criteria
- [ ] Unique app features are preserved with parity evidence in the ledger.
- [ ] App runs from IDE on Android and iOS.
- [ ] Core migrated flows behave consistently across Android and iOS.
- [ ] Shared UI follows `Screen`/`Content`, state-driven rendering, and UDF principles.
- [ ] Navigation is typed and stable.
- [ ] Platform-specific behavior is isolated at explicit boundaries.
- [ ] Shared resources and dependency conventions are applied.

## Copilot Agent Execution Protocol
1. Work phase-by-phase; do not proceed while any `P0` parity risk is unresolved.
2. Migrate in small vertical slices (one feature flow per batch).
3. Preserve behavior first; refactor second.
4. Update parity ledger after every batch with evidence.
5. If preserving a feature is ambiguous, propose 2-3 implementation options and ask for direction.
6. Stop and ask before removing any behavior that existed in Android.
7. Validate only with IDE run configurations defined by this plan.

## Deliverable Format Per Change Batch
For every batch, output:
- What changed (files/symbols).
- Which feature behavior was preserved.
- Why it changed (phase/checklist reference).
- Risk level (`P0`/`P1`/`P2`).
- IDE validation performed (Android/iOS/optional desktop/tests).
- Remaining follow-ups.


