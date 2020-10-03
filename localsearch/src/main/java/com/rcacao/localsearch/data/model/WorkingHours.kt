package com.rcacao.localsearch.data.model

import com.google.gson.annotations.SerializedName

class WorkingHours {

    @SerializedName("always_open")
    val alwaysOpen: Boolean = false

    @SerializedName("weekday_text")
    val weekDayText: List<String> = emptyList()

}
