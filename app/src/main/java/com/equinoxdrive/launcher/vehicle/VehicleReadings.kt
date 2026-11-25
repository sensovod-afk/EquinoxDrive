package com.equinoxdrive.launcher.vehicle

/**
 * Normalized readings consumed by the dashboard.
 * Null means the source has not provided a valid value.
 */
data class VehicleReadings(
    val fuelLitres: Double? = null,
    val fuelPercent: Double? = null,
    val consumptionLitresPer100Km: Double? = null,
    val consumptionLitresPerHour: Double? = null,
    val speedKmh: Double? = null,
    val engineRpm: Int? = null,
    val coolantTemperatureC: Double? = null,
    val batteryVoltage: Double? = null,
    val timestampMillis: Long = 0L,
    val source: DataSource = DataSource.NONE
)

enum class DataSource {
    NONE,
    OBD2_ELM327,
    FYT_CANBUS
}
