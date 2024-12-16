package com.example.dokizone.data.local.db.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.dokizone.data.local.db.entity.home_section.HomeSectionEntity

@Entity(
    primaryKeys = ["id", "section_name"]
)
data class AnimeCardHomeSectionCrossRef(
    @ColumnInfo(name = "id") val animeCardId: Int,
    @ColumnInfo(name = "section_name") val homeSectionName: String,
)

data class AnimeCardWithHomeSections(
    @Embedded val animeCard: AnimeCardEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "section_name",
        associateBy = Junction(AnimeCardHomeSectionCrossRef::class)
    )
    val homeSections: List<HomeSectionEntity>,
)

data class HomeSectionWithAnimeCards(
    @Embedded val homeSection: HomeSectionEntity,
    @Relation(
        parentColumn = "section_name",
        entityColumn = "id",
        associateBy = Junction(AnimeCardHomeSectionCrossRef::class)
    )
    val animeCards: List<AnimeCardEntity>,
)