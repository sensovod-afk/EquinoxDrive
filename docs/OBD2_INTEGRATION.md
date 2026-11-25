# OBD2 integration — Chevrolet Equinox 2012

## Confirmed by the owner
The vehicle has previously connected successfully using:
- ELM327 Bluetooth adapter
- Car Scanner app

The following values were visible in Car Scanner:
- Fuel level in litres
- Fuel level in percent
- Fuel consumption in L/100 km
- Fuel consumption in L/hour

This is evidence that the user's vehicle/adapter/app combination can obtain these values. It does **not** yet identify the exact PID commands or prove that every value is a standard SAE PID.

## Implementation approach
1. Implement an ELM327 Bluetooth transport.
2. Add a configurable PID/profile layer. Do not hardcode guessed GM commands.
3. Record the exact Car Scanner vehicle profile and PID definitions (or a diagnostic log/export) before implementing enhanced GM PIDs.
4. Normalize readings into the app's vehicle data model.
5. Show unavailable/stale values as "—"; never substitute invented values.

## Dashboard fields
- fuelLitres
- fuelPercent
- consumptionLitresPer100Km
- consumptionLitresPerHour

## Notes
- L/100 km is meaningful while moving; at very low speed or standstill the UI should avoid division-by-zero and can show the L/hour value instead.
- L/hour can be calculated from fuel rate only if the source provides a reliable fuel-rate signal. Prefer the value read from the verified Car Scanner PID/profile.
- Estimated range should be computed only when fuel quantity and a valid consumption average are available.
