package com.cairosquad.evolvefit.Repository

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.User

class FakeAuthRepository : AuthRepository {

    private val users = mutableMapOf<String, User>()
    private var currentUser: User? = null

    override suspend fun register(data: User): Result<Unit> {
        if (users.containsKey(data.name)) {
            return Result.failure(Exception("User already exists"))
        }
        users[data.name] = data
        return Result.success(Unit)
    }

    override suspend fun login(name: String, password: String): Result<User> {
        val data = users[name]
        return if (data != null && data.password == password) {
            val user = User(
                name = data.name,
                email = data.email,
                dateOfBirth = data.dateOfBirth,
                password = data.password,
                gender = data.gender,
                unit = data.unit,
                height = data.height,
                weight = data.weight,
                goal = data.goal,
                tools = data.tools,
                notifications = data.notifications,
                workoutDays = data.workoutDays
            )
            currentUser = user
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return currentUser != null
    }

    override suspend fun logout() {
        currentUser = null
    }
}

