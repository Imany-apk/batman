package iman.mohammadpour.batman.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import iman.mohammadpour.batman.R
import iman.mohammadpour.batman.extensions.load
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.extras?.getString(EXTRA_MOVIE_ID)?.let {
            img_poster.load(it)
        } ?: finish()
    }

}