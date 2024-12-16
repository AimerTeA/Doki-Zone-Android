package com.example.dokizone.data.local.db.type_converter.home_section

import androidx.room.TypeConverter
import com.example.dokizone.core.Constants

class HomeSectionTypeConverter {
    @TypeConverter
    fun fromHomeSection(section: Constants.HomeSection): String {
        return section.name
    }

    @TypeConverter
    fun toHomeSection(section: String): Constants.HomeSection {
        return Constants.HomeSection.valueOf(section)
    }
}