package com.denicks21.roomdatabase.repository

import androidx.lifecycle.MutableLiveData
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.database.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {
    val allUsers = MutableLiveData<List<User>>()
    val foundUser = MutableLiveData<User>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(newUser)
        }
    }

    fun updateUserDetails(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.updateUserDetails(newUser)
        }
    }

    fun getAllUsers() {
        coroutineScope.launch(Dispatchers.IO) {
            allUsers.postValue(userDao.getAllUsers())
        }
    }

    fun deleteUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }

    fun findUserById(userId: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundUser.postValue(userDao.findUserById(userId))
        }
    }
}