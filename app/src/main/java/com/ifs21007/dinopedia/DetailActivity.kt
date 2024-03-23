package com.ifs21007.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21007.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var gen: Gen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gen = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAM, Gen ::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAM)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (gen != null) {
            supportActionBar?.title = "About ${gen!!.dino}"
            loadData(gen!!)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                // Tambahkan kode aksi yang diinginkan di sini
                openProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openProfile(){
        val intent = Intent(this@DetailActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun loadData(gen: Gen) {
        binding.ivDetail.setImageResource(gen.icon)
        binding.tvDetailTitle.text = gen.dino
        binding.tvDetDesDin.text = gen.des
        binding.tvDetFoodDino.text = gen.food
        binding.tvDetCharDino.text = gen.chars
        binding.tvDetHeightDino.text = gen.height
        binding.tvDetWeightDino.text = gen.weight
        binding.tvDetWsDino.text = gen.weak
        binding.tvDetHabitDino.text = gen.habit
        binding.tvDetBehavDino.text = gen.behav
    }

    companion object {
        const val EXTRA_FAM = "extra_dino"
    }

}
