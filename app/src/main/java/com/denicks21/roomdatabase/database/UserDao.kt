package com.denicks21.roomdatabase.database

import androidx.room.*
import com.denicks21.roomdatabase.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE userId = :usId")
    fun findUserById(usId: String): User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Update
    suspend fun updateUserDetails(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}