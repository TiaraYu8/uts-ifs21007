package com.ifs21007.dinopedia


import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21007.dinopedia.databinding.ActivityDetailfamBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailfamBinding
    private var fam: Fam? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailfamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fam = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAM,
                Fam::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAM)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (fam != null) {
            supportActionBar?.title = "${fam!!.dino}"
            loadData(fam!!)
        } else {
            finish()
        }
    }
    private fun loadData(fam: Fam) {
        binding.ivDetailedIcon.setImageResource(fam.icon)
        binding.tvDetailName.text = fam.dino
        binding.tvDetailedSanskrit.text = fam.des
        binding.tvDetailedKeyBenefits.text = fam.habit
        binding.tvDetailedMentalBenefits.text = fam.per
        binding.tvDetailedHowToDo.text = fam.chare
        binding.tvDetailedBehav.text = fam.behav


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_FAM = "extra_pose"
    }
}