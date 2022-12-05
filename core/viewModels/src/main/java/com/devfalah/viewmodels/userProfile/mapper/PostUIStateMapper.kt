package com.devfalah.viewmodels.userProfile.mapper

import com.devfalah.entities.Post
import com.devfalah.viewmodels.userProfile.PostUIState

fun List<Post>.toUIState() = map { it.toUIState() }

fun Post.toUIState(): PostUIState {
    return PostUIState(
        postId = id,
        posterImage = publisherImageUrl,
        posterName = publisher,
        privacy = privacy,
        createdData = createdTime,
        totalLikes = totalLikes,
        totalComments = totalComments,
        isSaved = false,
        isLikedByUser = isLiked,
        postImage = imageUrl,
        postContent = content
    )
}