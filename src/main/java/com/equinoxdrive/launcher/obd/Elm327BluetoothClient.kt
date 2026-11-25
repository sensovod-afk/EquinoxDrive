package com.equinoxdrive.launcher.obd

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.util.UUID

/**
 * Bluetooth Classic SPP transport for a paired ELM327.
 * Call connect/read/write from a worker thread, never the main thread.
 * The caller must request BLUETOOTH_CONNECT on Android 12+ before use.
 */
class Elm327BluetoothClient {
    companion object {
        val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }

    private var socket: BluetoothSocket? = null

    @SuppressLint("MissingPermission")
    @Throws(IOException::class)
    fun connect(device: BluetoothDevice) {
        close()
        val candidate = device.createRfcommSocketToServiceRecord(SPP_UUID)
        candidate.connect()
        socket = candidate
    }

    @Throws(IOException::class)
    fun send(command: String): String {
        val active = socket ?: throw IOException("ELM327 is not connected")
        val output = active.outputStream
        val input = active.inputStream
        output.write((command.trim() + "\r").toByteArray(Charsets.US_ASCII))
        output.flush()

        val response = StringBuilder()
        val buffer = ByteArray(256)
        // ELM327 terminates each command response with the '>' prompt.
        while (true) {
            val count = input.read(buffer)
            if (count < 0) throw IOException("ELM327 connection closed")
            response.append(String(buffer, 0, count, Charsets.US_ASCII))
            if (response.contains(">")) break
        }
        return Elm327Protocol.cleanResponse(response.toString())
    }

    fun initialize(): List<String> = Elm327Protocol.initializationCommands.map(::send)

    fun close() {
        try { socket?.close() } catch (_: IOException) { }
        socket = null
    }

    fun isConnected(): Boolean = socket?.isConnected == true
}
