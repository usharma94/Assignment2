package ca.sheridan.sharmupm.assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class GameScore(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "die1")
    val die1: Int,

    @ColumnInfo(name = "die2")
    val die2: Int,

    @ColumnInfo(name = "die3")
    val die3: Int,

    @ColumnInfo(name = "total")
    val total: Int
)