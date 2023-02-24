package com.denicks21.roomdatabase.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "userId")
    var userId: Long,

    @ColumnInfo(name = "userName")
    var userName: String,

    @ColumnInfo(name = "userSurname")
    var userSurname: String,

    @ColumnInfo(name = "userCity")
    var userCity: String,

    @ColumnInfo(name = "userPhone")
    var userPhone: Long,

    @ColumnInfo(name = "userEmail")
    var userEmail: String
) : Parcelable