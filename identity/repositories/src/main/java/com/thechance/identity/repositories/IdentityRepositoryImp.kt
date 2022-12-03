package com.thechance.identity.repositories

import com.thechance.identity.entities.User
import com.thechance.identity.repositories.mappers.toEntity
import com.thechance.identity.usecases.IdentityRepository
import javax.inject.Inject

class IdentityRepositoryImp @Inject constructor(
    private val remoteDataSource: IdentityDataSource
) : IdentityRepository {

    override suspend fun login(userName: String, password: String): User {
        return remoteDataSource.login(userName, password).toEntity()
    }

    override suspend fun signup(
        firstname: String,
        lastname: String,
        email: String,
        reEmail: String,
        gender: String,
        birthdate: String,
        username: String,
        password: String
    ): Boolean {
        return remoteDataSource.signup(
            firstname = firstname,
            lastname = lastname,
            email = email,
            reEmail = reEmail,
            gender = gender,
            birthdate = birthdate,
            username = username,
            password = password
        )
    }
}