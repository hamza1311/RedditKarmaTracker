package com.example.redditkarmatracker.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.redditkarmatracker.R
import com.example.redditkarmatracker.viewmodels.KarmaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var karmaViewModel: KarmaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        karmaViewModel = ViewModelProviders.of(this).get(KarmaViewModel::class.java)
        mainAct_refresh_button.setOnClickListener {
            karmaViewModel.insert()
        }

        mainAct_stats_button.setOnClickListener {
            startActivity(Intent(this, KarmaStatsActivity::class.java))
        }

        karmaViewModel.karma.observe(this, Observer {
            it?.let { karma ->
                if (karma.isNotEmpty()) {
                    mainAct_karma_textView.text = getString(R.string.my_karma, karma[0].currentKarma.toString())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_login_button) {
            startActivity(Intent(this, NewUserLoginActivity::class.java))
            Toast.makeText(this, "login", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
