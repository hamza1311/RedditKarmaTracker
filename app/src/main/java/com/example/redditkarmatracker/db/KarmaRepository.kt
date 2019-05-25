package com.example.redditkarmatracker.db

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.redditkarmatracker.App
import com.example.redditkarmatracker.models.Karma
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KarmaRepository(private val karmaDao: KarmaDao) {

    @WorkerThread
    suspend fun insert() {
        karmaDao.addKarma(karma = getKarmaFromReddit())
    }

    fun getAll() = karmaDao.getKarma()

    private suspend fun getKarmaFromReddit(): Karma {
        var totalKarma = 0
        withContext(Dispatchers.IO) {
            val linkKarma = App().getAccountHelper().reddit.me().query().account!!.linkKarma /*3484*/
            val commentKarma = App().getAccountHelper().reddit.me().query().account!!.commentKarma /* 3485*/
            totalKarma = linkKarma + commentKarma + 5
        }
        Log.d("karma", "$totalKarma")
        return Karma(currentKarma = totalKarma, currentTime = System.currentTimeMillis())
    }
}