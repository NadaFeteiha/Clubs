package com.devfalah.usecases

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import com.devfalah.usecases.util.Constants.SCROLL_UP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.roundToInt

class GetHomeThreadsUseCase @Inject constructor(
    private val clubRepository: ClubRepository
) {
    private val savedPosts: MutableList<Int> = mutableListOf()
    private var userId = -1
    private var postsCount = 0

    suspend operator fun invoke(): Flow<List<Post>> {
        userId = clubRepository.getUserId()
        val homePosts = clubRepository.getHomePosts().map {
            postsCount = it.size
            it.map { post ->
                post.copy(isMyPost = userId == post.publisherId)
            }
        }
        return homePosts
    }

    suspend fun loadData(scrollDirection: Int): Boolean {
        val page = if (scrollDirection == SCROLL_UP || postsCount == 0) {
            1
        } else {
            (postsCount / 10.0).roundToInt() + 1
        }
        return refreshData(page)
    }

    private suspend fun refreshData(page: Int): Boolean {
        if (userId == -1) {
            userId = clubRepository.getUserId()
        }
        val homePosts = clubRepository.getUserHomePosts(userId, page).map { post ->
            if (post.id in savedPosts) {
                post.copy(isSaved = false, isMyPost = userId == post.publisherId)
            } else {
                post.copy(isMyPost = userId == post.publisherId)
            }
        }
        clubRepository.addHomePosts(homePosts)
        return true
    }

}