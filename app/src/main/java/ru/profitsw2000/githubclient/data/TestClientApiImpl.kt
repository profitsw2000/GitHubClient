package ru.profitsw2000.githubclient.data


import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.profitsw2000.githubclient.data.repository.LocalRepoImpl
import ru.profitsw2000.githubclient.data.retrofit.GitHubApi
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.entities.User
import ru.profitsw2000.githubclient.domain.entities.UserDetails
import ru.profitsw2000.githubclient.domain.entities.UserProfile
import ru.profitsw2000.githubclient.domain.entities.UserRepo

class TestClientApiImpl : ClientApi {
    private val localRepo = LocalRepoImpl()
    //retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)
=======
import ru.profitsw2000.githubclient.data.repository.LocalRepoImpl
import ru.profitsw2000.githubclient.domain.ClientApi
import ru.profitsw2000.githubclient.domain.entities.UserProfile

class TestClientApiImpl : ClientApi {
    private val localRepo = LocalRepoImpl()

    override fun getUserList(): List<UserProfile>? {
        val userList = localRepo.getAllUsers()
        Thread.sleep(2_000)
        return userList
    }


    override fun getRxUserList(): Single<List<User>> {
        return api.listUsers()
    }

    override fun getRxUserInfo(login: String): Single<UserDetails> {
        return api.userInfo(login)
    }

    override fun getRxUserRepositories(login: String): Single<List<UserRepo>> {
        return api.listRepos(login)
    }

=======
    override fun createNewUser(
        name: String,
        info: String,
        city: String,
        avatarUrl: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserInfo(id: Int, info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserCity(id: Int, info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeUserAvatar(info: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun createUserRepository(userRepository: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUserRepository(userRepository: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUserFromDatabase(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}