package com.equinoxdrive.launcher.obd

/** A PID definition is not proof that a particular ECU supports it. */
data class FuelPidDefinition(
    val id: String,
    val title: String,
    val request: String,
    val ecuHeader: String? = null,
    val unit: String,
    val formulaDescription: String,
    val isStandardObdPid: Boolean = false
)

data class FuelReadings(
    val levelLitres: Double? = null,
    val levelPercent: Double? = null,
    val instantLitresPer100Km: Double? = null,
    val litresPerHour: Double? = null,
    val rangeKm: Double? = null
)

object EquinoxFuelPidProfile {
    // Mode 01 PID 2F: fuel tank level input, when supported. A * 100 / 255.
    val standardFuelLevelPercent = FuelPidDefinition(
        id = "obd_fuel_level_percent",
        title = "Fuel tank level",
        request = Elm327Protocol.mode01(0x2F),
        unit = "%",
        formulaDescription = "A * 100 / 255",
        isStandardObdPid = true
    )

    // Exact commands/formulas must be copied from the user's Car Scanner Extra PIDs.
    val requiredCarScannerExtraPids = listOf(
        FuelPidDefinition("gm_fuel_litres", "Fuel remaining", "", unit = "L",
            formulaDescription = "Awaiting exact Car Scanner request and formula"),
        FuelPidDefinition("fuel_l_per_100km", "Instant fuel consumption", "", unit = "L/100 km",
            formulaDescription = "Awaiting exact Car Scanner request and formula"),
        FuelPidDefinition("fuel_l_per_hour", "Fuel consumption per hour", "", unit = "L/h",
            formulaDescription = "Awaiting exact Car Scanner request and formula")
    )
}