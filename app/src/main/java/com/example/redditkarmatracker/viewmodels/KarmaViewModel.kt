package com.example.redditkarmatracker.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.redditkarmatracker.App
import com.example.redditkarmatracker.db.KarmaDatabase
import com.example.redditkarmatracker.db.KarmaRepository
import com.example.redditkarmatracker.models.Karma
import com.example.redditkarmatracker.models.getLastLoggedInUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KarmaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: KarmaRepository
    val karma: LiveData<List<Karma>>

    init {
        val karmaDao = KarmaDatabase.getDatabase(application).karmaDao()
        repository = KarmaRepository(karmaDao)
        viewModelScope.launch(Dispatchers.IO) {
            // This will re-login every time an instance of this view model is created. If you think you can do it better, submit a pull request
            if (App.tokenStore!!.size() > 0) {
                login(application).invokeOnCompletion {
                    insert()
                }
            }
        }
        karma = repository.getAll()
    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        repository.insert()
    }

    private suspend fun login(application: Application) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val username = application.getLastLoggedInUser()
            Log.d("ddd", "user: $username")
            App.accountHelper.switchToUser(username)
            Log.d("ddd", "View Model")
        }
    }
}