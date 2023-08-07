package com.tamse.mytv_small.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.TopHeadLine

@Entity(tableName = "channel")
data class DatabaseChannel constructor(

    @PrimaryKey
    val contentID: String,

    val contentTitle: String,

    val contentImageUrl: String,

    val contentImageSmall: String,

    val typeID: Int,

    val typeGroup: Int,

    val recommendation: Int,

    val sortOrder: String,

    val listId: String,

    val sortOrderList: String,

    val tvod: String,

//    val productPluginIdList: String,

    val hasSchedule: String
)

fun List<DatabaseChannel>.asDomainModel(): List<Channel> {
    return map {
        Channel(
            contentID = it.contentID,
            contentTitle = it.contentTitle,
            contentImageSmall = it.contentImageSmall,

            contentImageUrl = it.contentImageUrl,
            typeID = it.typeID,
            typeGroup = it.typeGroup,

            recommendation = it.recommendation,
            sortOrder = it.sortOrder,
            listId = it.listId,

            sortOrderList = it.sortOrderList,
            tvod = it.tvod,
//            productPluginIdList = it.productPluginIdList,

            hasSchedule = it.hasSchedule
            )
    }
}