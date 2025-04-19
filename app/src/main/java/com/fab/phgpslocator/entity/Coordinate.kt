package com.fab.phgpslocator.entity

import kotlinx.serialization.Serializable

//@Entity(tableName = "coordinate")
@Serializable
data class Coordinate(
    //@PrimaryKey(autoGenerate = true)
    //@SerializedName("id")
    val id: Int? = null,
    //@SerializedName("latitude")
    val latitude: Double? = null,
    //@SerializedName("longitude")
    val longitude: Double? = null,
)