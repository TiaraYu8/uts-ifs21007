package com.ifs21007.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21007.dinopedia.databinding.ActivityGenBinding

class GenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenBinding
    private lateinit var fam: Fam
    private val dataGen = ArrayList<Gen>()

    companion object {
        const val EXTRA_FAM = "extra_dino"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fam = intent.getParcelableExtra(EXTRA_FAM)!!

        binding.rvGen.setHasFixedSize(true)
        dataGen.addAll(getListGen())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListGen(): ArrayList<Gen> {
        val abeliIcon = resources.obtainTypedArray(R.array.abeli_icon)
        val abeliName = resources.getStringArray(R.array.abelisauridae_genera)
        val abeliDes = resources.getStringArray(R.array.abelisauridae_description)
        val abeliChara = resources.getStringArray(R.array.abelisauridae_characteristic)
        val abeliFood = resources.getStringArray(R.array.abelisauridae_food)
        val abeliHeight = resources.getStringArray(R.array.abelisauridae_height)
        val abeliWeight = resources.getStringArray(R.array.abelisauridae_weight)
        val abeliws = resources.getStringArray(R.array.abelisauridae_weakness_strength)
        val abeliHabits = resources.getStringArray(R.array.abelisauridae_habitats)
        val abeliBehav = resources.getStringArray(R.array.abelisauridae_behavior)

        val sIn = fam.sIn
        val eIn = fam.eIn
        val listDino = ArrayList<Gen>()
        for (i in sIn..eIn) {
            val list = Gen(
                abeliIcon.getResourceId(i, -1), abeliName[i], abeliDes[i],
                abeliChara[i], abeliFood[i], abeliHeight[i], abeliWeight[i], abeliws[i], abeliHabits[i],
                abeliBehav[i]
            )
            listDino.add(list)
        }
        return listDino
    }

    private fun showRecyclerList() {
        // Menentukan spanCount berdasarkan orientasi layar
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            3 // Jika dalam orientasi lanskap
        } else {
            2 // Jika dalam orientasi potret
        }

        // Menggunakan GridLayoutManager dengan spanCount yang sesuai
        val layoutManager = GridLayoutManager(this, spanCount)

        binding.rvGen.layoutManager = layoutManager

        val listGenAdapter = ListGenAdapter(dataGen)
        binding.rvGen.adapter = listGenAdapter
        listGenAdapter.setOnItemClickCallback(object :
            ListGenAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Gen) {
                showSelectedDino(data)
            }
        })
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
        val intent = Intent(this@GenActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun showSelectedDino(gen: Gen) {
        val intentWithData = Intent(
            this@GenActivity,
            DetailActivity::class.java
        )
        intentWithData.putExtra(DetailActivity.EXTRA_FAM, gen)
        startActivity(intentWithData)
    }

}
