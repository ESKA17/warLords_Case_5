package kz.jusan.tinderformentors.presentation.fragments.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.jusan.tinderformentors.presentation.models.Post
import kz.jusan.tinderformentors.util.Constants.POSTS_LIST

class NewsViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>().apply {
        value = POSTS_LIST
    }
    val posts: LiveData<List<Post>> = _posts

    fun addPost(post: Post) {
        val currentList = _posts.value?.toMutableList()
        currentList?.add(post)
        _posts.value = currentList
    }

    fun getPostsCount(): Int {
        return posts.value?.size ?: 0
    }

}