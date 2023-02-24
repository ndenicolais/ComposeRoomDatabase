package com.denicks21.roomdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.denicks21.roomdatabase.model.User
import com.denicks21.roomdatabase.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun getAllUsers() {
        userRepository.getAllUsers()
    }

    fun addUser(user: User) {
        userRepository.addUser(user)
        getAllUsers()
    }

    fun updateUserDetails(user: User) {
        userRepository.updateUserDetails(user)
        getAllUsers()
    }

    fun findUserById(usId: String) {
        userRepository.findUserById(usId)
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        getAllUsers()
    }

    val userList: LiveData<List<User>> = userRepository.allUsers
    val foundUser: LiveData<User> = userRepository.foundUser
}