package com.example.dokizone.data.local.db.dao.home_section

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dokizone.data.local.db.entity.home_section.HomeSectionEntity

@Dao
interface HomeSectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeSections(sections: List<HomeSectionEntity>)
}