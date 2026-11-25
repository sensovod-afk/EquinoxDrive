# OBD2 / ELM327 integration

Vehicle: Chevrolet Equinox 2012 LTZ  
Confirmed adapter: Bluetooth ELM327  
Working Car Scanner profile: OBD2/EOBD + CAN 11-bit (2004–present), Extra PIDs enabled.

## Integration plan

1. Connect to the paired Bluetooth Classic ELM327 device using RFCOMM SPP.
2. Initialize the adapter (reset, echo off, linefeeds off, spaces off, adaptive timing, automatic protocol detection).
3. Query standard OBD-II PIDs and verify ECU support before polling.
4. Add exact Car Scanner Extra PID definitions for fuel litres, L/100 km and L/hour once request bytes, ECU header and formula are available.
5. Publish nullable readings; show an em dash when unsupported or stale. Never substitute demo numbers for live vehicle data.

## Known standard PID

- Mode 01 PID 2F: fuel tank level input, if supported. Formula: A × 100 / 255 (%).

## Important

The Car Scanner profile name confirms the selected protocol family, but it does not reveal the actual Extra PID definitions. Do not hardcode guessed GM commands. ATSP0 is used initially for automatic protocol detection; protocol 6 (11-bit, 500 kbit/s CAN) should only be forced after confirming the adapter and ECU connection.

## Required capture from Car Scanner

For each working fuel sensor, capture:
- sensor name;
- request/command bytes;
- ECU header/address, if shown;
- formula / equation;
- units and any min/max/scaling.

These definitions are required to reproduce the same readings independently of Car Scanner.