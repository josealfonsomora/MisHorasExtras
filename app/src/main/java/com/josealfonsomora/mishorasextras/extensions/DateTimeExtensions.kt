package com.josealfonsomora.mishorasextras.extensions

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun Long.toZonedDateTime() =
    ZonedDateTime.ofInstant(Instant.ofEpochSecond(this), ZoneId.systemDefault())

fun ZonedDateTime.print(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}
