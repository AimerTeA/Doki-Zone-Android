package com.example.dokizone.data.local.db.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dokizone.core.Constants
import com.example.dokizone.data.local.db.entity.home_section.HomeSectionEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreFillDokiZoneDatabaseCallback : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        insertHomeSections(db)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun insertHomeSections(db: SupportSQLiteDatabase) {
        GlobalScope.launch(Dispatchers.IO) {
            val sections = Constants.HomeSection.entries.map { section -> HomeSectionEntity(section.name) }
            sections.forEach { section ->
                db.execSQL(
                    """
                        INSERT INTO home_sections (section_name) 
                        VALUES ('${section.sectionName}')
                    """.trimMargin()
                )
            }
        }
    }
}