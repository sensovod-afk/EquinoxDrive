package com.equinoxdrive.launcher.obd

/** ELM327 command helpers. No guessed GM Extra PID formulas are included. */
object Elm327Protocol {
    const val HEADER_11_BIT = "7E0"
    const val RESPONSE_HEADER_11_BIT = "7E8"
    val initializationCommands = listOf("ATZ", "ATE0", "ATL0", "ATS0", "ATH1", "ATAT1", "ATSP0")
    fun mode01(pid: Int): String = "01" + pid.toString(16).uppercase().padStart(2, '0')
    fun customRequest(header: String, payload: String): List<String> = listOf("ATSH" + header, payload)
    fun cleanResponse(raw: String): String =
        raw.replace(">", "").replace("\r", " ").replace("\n", " ").trim().uppercase()
}