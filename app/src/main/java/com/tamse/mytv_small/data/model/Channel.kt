package com.tamse.mytv_small.data.model

import com.squareup.moshi.Json

data class Channel(

//    val id: String,
//
//    @Json(name = "img_src")
//    val imgSrcUrl: String

    @Json(name = "CONTENT_ID")
    val contentID: String? = "",

    @Json(name = "CONTENT_TITLE")
    val contentTitle: String? = "",

    @Json(name = "CONTENT_IMAGE_URL")
    val contentImageUrl: String? = "",

    @Json(name = "CONTENT_IMAGE_SMALL")
    val contentImageSmall: String? = "",

    @Json(name = "TYPE_ID")
    val typeID: Int? = 0,

    @Json(name = "TYPE_GROUP")
    val typeGroup: Int? = 0,

    @Json(name = "RECOMMENDATION")
    val recommendation: Int? = 0,

    @Json(name = "SORT_ORDER")
    val sortOrder: String? = "",

    @Json(name = "LIST_ID")
    val listId: String? = "",

    @Json(name = "SORT_ORDER_LIST")
    val sortOrderList: String? = "",

    @Json(name = "TVOD")
    val tvod: String? = "",

    @Json(name = "PRODUCT_PLUGIN_ID_LIST")
    val productPluginIdList: String? = "",

    @Json(name = "HAS_SCHEDULE")
    val hasSchedule: String? = ""


)