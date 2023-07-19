package com.tamse.mytv_small.data.model

import com.squareup.moshi.Json

data class ChannelDetail(
    @Json(name = "channel_id")
    val channelId: String? = "",

    @Json(name = "sort_order")
    val sortOrder: String? = "",

    @Json(name = "category_id")
    val categoryId: String? = "",

    @Json(name = "mf_code")
    val mfCode: String? = "",

    val hd: String? = "",

    @Json(name = "product_plugin_id")
    val productPluginId: String? = "",

    val tvod: String? = "",

    val play: Int? = 0,

    val favorite: String? = "",

    val drm: String? = "",

    @Json(name = "4k")
    val _4k: String? = "",

    @Json(name = "provider_id")
    val providerId: String? = "",

    @Json(name = "cate_list")
    val cateList: String? = "",

    @Json(name = "order_cate_list")
    val orderCateList: String? = "",

    val tstv: String? = "",

    @Json(name = "channel_name")
    val channelName: String? = "",

//    val logo: String? = "",

    val message: String? = ""
)
