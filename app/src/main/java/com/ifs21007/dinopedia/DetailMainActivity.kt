package com.ifs21007.dinopedia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ifs21007.dinopedia.databinding.ActivityDetailMainBinding

class DetailMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMainBinding
    private var fam: Fam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fam = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DetailMainActivity.EXTRA_FAM, Fam::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DetailMainActivity.EXTRA_FAM)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (fam != null) {
            supportActionBar?.title = "About ${fam!!.title}"
            loadData(fam!!)
        } else {
            finish()
        }

        binding.button.setOnClickListener {
            val intentWithData = Intent(
                this@DetailMainActivity,
                GenActivity::class.java
            )
            intentWithData.putExtra(GenActivity.EXTRA_FAM, fam)
            startActivity(intentWithData)
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
        val intent = Intent(this@DetailMainActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun loadData(fam: Fam) {
        binding.ivDetail.setImageResource(fam.icon)
        binding.tvDetailTitle.text = fam.title
        binding.tvDetDesFam.text = fam.desc
        binding.tvDetPeriodFam.text = fam.per
        binding.tvDetCharFam.text = fam.chara
        binding.tvDetHabitFam.text = fam.habit
        binding.tvDetBehavFam.text = fam.behavi
    }

    companion object {
        const val EXTRA_FAM = "extra_dino"
    }
}