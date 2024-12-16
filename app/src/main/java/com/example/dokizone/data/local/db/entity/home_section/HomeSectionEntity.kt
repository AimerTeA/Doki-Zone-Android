package com.example.dokizone.data.local.db.entity.home_section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_sections")
data class HomeSectionEntity(
    @PrimaryKey
    @ColumnInfo("section_name")
    val sectionName: String
)