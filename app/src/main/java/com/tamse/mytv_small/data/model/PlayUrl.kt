package com.tamse.mytv_small.data.model

import com.squareup.moshi.Json

data class PlayUrl(
    val url: String = "",

    @Json(name = "node_addr")
    val nodeAddr: String = "",

    @Json(name = "link_to_cdn")
    val linkToCdn: String = "",

    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String = "",

    @Json(name = "ad_info")
    val adInfo: String = "",

    @Json(name = "ad_id")
    val adId: String = "",

    @Json(name = "channel_copyright")
    val channelCopyright: List<String> = listOf(),

    val e: EncryptUrlModel? = null


)
