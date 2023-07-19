package com.tamse.mytv_small.data.model
import com.squareup.moshi.Json

data class TopHeadLine(
    val result: Int = 0,
    @Json(name = "data")
    val channels: List<Channel> = ArrayList(),
    @Json(name = "IP_SERVER")
    val ipserver: String = "",
    @Json(name = "time_exec")
    val timeExec: String = "",
    @Json(name = "ser_time")
    val serTime: String = ""
)
