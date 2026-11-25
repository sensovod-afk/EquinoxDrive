# Equinox Drive Launcher

Android launcher/dashboard for Chevrolet Equinox (2012 LTZ) and UIS7870 / FYT-based head units.

## Current status
- Initial Android project scaffold.
- Landscape dashboard UI with clearly marked unavailable/demo vehicle data.
- Vehicle data integration is intentionally isolated behind interfaces.
- Real CANBUS/FYT signals are **not yet verified** on the target head unit.

## Planned modules
- Dashboard and configurable widgets
- Media controls and equalizer shortcut
- Vehicle data: speed, fuel level, instantaneous/average consumption, estimated range
- Battery voltage
- FYT/SYU IPC and CANBUS adapter (after on-device API verification)
- Optional OBD-II adapter

## Build
Open this project in Android Studio (Hedgehog or newer), sync Gradle, then run the `app` configuration.

## Important
The dashboard must never present guessed values as live vehicle data. Until a real data source is connected, unsupported values should appear as "—" or in an explicitly labelled demo mode.
