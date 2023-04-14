package com.example.meebooapp.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "date_time") var dateTime: String? = "",
    @ColumnInfo(name = "subTitle") var subTitle: String? = "",
    @ColumnInfo(name = "note_text") var note_text: String? = "",
    @ColumnInfo(name = "image_path") var image: String? = "",
    @ColumnInfo(name = "color") var color: String? = "",
    @ColumnInfo(name = "web_link") var webLink: String? = ""

) : Serializable {
    @NonNull
    override fun toString(): String {
        return "$title : $dateTime"
    }

}