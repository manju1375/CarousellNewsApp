package com.carousell.newsapp.common

import org.joda.time.DateTime
import org.joda.time.Period
import java.util.*


fun getDateElapsedString(timeCreated: Long): String{
    val timeDate = timeCreated.toTimeDate()
    val createdDate = DateTime(timeDate.get(Calendar.YEAR), timeDate.get(Calendar.MONTH), timeDate.get(Calendar.DAY_OF_MONTH), timeDate.get(Calendar.HOUR), timeDate.get(Calendar.MINUTE), timeDate.get(Calendar.SECOND), 0)
    val now = DateTime()
    val period = Period(createdDate, now)
    val seconds = period.seconds
    val minutes = period.minutes
    val hours = period.hours
    val days = period.days
    val weeks = period.weeks
    val months = period.months
    val years = period.years

    var elapsedTime = ""
    if (years > 0)
        elapsedTime = "$years years ago"
    else if (months-1 > 0)
        elapsedTime = "${months-1} months ago"
    else if (weeks-1 > 0 && weeks < 4)
        elapsedTime = "${weeks-1} weeks ago"
    else if (days > 0 && days < 31)
        elapsedTime = "${days} days ago"
    else if (hours-1 > 0 && hours < 24)
        elapsedTime = "${hours-1} hours ago"
    else if (minutes-1 > 0 && minutes < 60)
        elapsedTime = "${minutes-1} minutes ago"
    else if (seconds > 0)
        elapsedTime = "$seconds seconds ago"
    return elapsedTime
}

fun Long.toTimeDate():Calendar {
    val dateTime = java.util.Date( this*1000L)
    val calendar = Calendar.getInstance()
    calendar.time = dateTime
    return calendar
}