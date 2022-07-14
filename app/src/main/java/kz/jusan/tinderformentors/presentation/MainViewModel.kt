package kz.jusan.tinderformentors.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.jusan.tinderformentors.presentation.models.Match
import kz.jusan.tinderformentors.util.Constants


class MainViewModel : ViewModel() {

    private val _matches = MutableLiveData<List<Match>>().apply {
        value = Constants.MATCHES_LIST
    }
    val matches: LiveData<List<Match>> = _matches

    fun addMatch(match: Match) {
        val currentList = _matches.value?.toMutableList()
        currentList?.add(match)
        _matches.value = currentList
    }

    fun getMatchesCount(): Int {
        return matches.value?.size ?: 0
    }

}