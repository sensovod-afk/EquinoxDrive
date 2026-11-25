package com.equinoxdrive.launcher.obd

import com.equinoxdrive.launcher.vehicle.VehicleReadings

/**
 * Transport-independent contract. The ELM327 Bluetooth implementation will
 * be added after the Car Scanner profile/PID definitions are confirmed.
 */
interface Obd2DataSource {
    suspend fun connect(): Boolean
    suspend fun readVehicleReadings(): VehicleReadings
    suspend fun disconnect()
}

data class ObdPidDefinition(
    val name: String,
    val command: String,
    val unit: String,
    val description: String,
    val isEnhancedManufacturerPid: Boolean = false
)
