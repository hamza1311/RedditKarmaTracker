package com.example.redditkarmatracker.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditkarmatracker.R
import com.example.redditkarmatracker.adapters.KarmaStatsAdapter
import com.example.redditkarmatracker.models.Karma
import com.example.redditkarmatracker.viewmodels.KarmaViewModel
import kotlinx.android.synthetic.main.activity_karma_stats.*

class KarmaStatsActivity : AppCompatActivity() {
    private lateinit var karmaViewModel: KarmaViewModel
    private lateinit var statsAdapter: KarmaStatsAdapter
    private lateinit var karmaLayoutManager: LinearLayoutManager
    private lateinit var karmaList: MutableList<Karma>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karma_stats)
        karmaViewModel = ViewModelProviders.of(this).get(KarmaViewModel::class.java)
        karmaList = mutableListOf()
        statsAdapter = KarmaStatsAdapter(this, karmaList)
        karmaLayoutManager = LinearLayoutManager(this)

        statsAct_recyclerView.apply {
            adapter = statsAdapter
            layoutManager = karmaLayoutManager
        }

        karmaViewModel.karma.observe(this, Observer {
            it?.let { _karmaList ->
                var i = 0
                karmaList.clear()
                _karmaList.forEach { karma ->
                    Log.d("ddd", "i: $i, karma: ${karma.currentKarma}")
                    karmaList.add(karma)
                    i++
                }
                statsAdapter.notifyDataSetChanged()
            }
        })


    }
}
