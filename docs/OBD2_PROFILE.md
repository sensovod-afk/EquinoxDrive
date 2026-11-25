# Car Scanner connection profile

Owner-confirmed Car Scanner profile:
- Protocol/profile: OBD2/EOBD + CAN 11-bit (2004–present)
- Extra PIDs: enabled

Vehicle: Chevrolet Equinox 2012 LTZ
Adapter: Bluetooth ELM327

## Integration implications
- Use ELM327 over Bluetooth with the selected CAN 11-bit protocol (11-bit CAN identifiers; typically 500 kbit/s on this profile, subject to adapter negotiation).
- Keep manufacturer-specific/extra PID definitions in a separate profile configuration.
- Do not assume the displayed fuel values map to standard SAE PIDs. The exact extra PID definitions, equations, headers, and response parsing must be obtained from Car Scanner's export/log or settings.
- Preserve support for standard OBD-II PIDs as a fallback.
- Confirm adapter chipset/firmware and Bluetooth transport type (Classic SPP vs BLE) before choosing the Android connection implementation.

## Confirmed displayed values
- Fuel quantity (litres)
- Fuel level (percent)
- Fuel consumption (L/100 km)
- Fuel consumption (L/hour)
