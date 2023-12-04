package com.sithuheinn.mm.effiecientweatherapp.util


import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime


fun LocalDateTime.isNightTime(): Boolean {
    val nightStartTime = LocalTime.of(18, 0)  // Adjust as needed, e.g., 6:00 PM
    val nightEndTime = LocalTime.of(6, 0)     // Adjust as needed, e.g., 6:00 AM

    val localTime = this.toLocalTime()

    return localTime.isAfter(nightStartTime) || localTime.isBefore(nightEndTime)
}