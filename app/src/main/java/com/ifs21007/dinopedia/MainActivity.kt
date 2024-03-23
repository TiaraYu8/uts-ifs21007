package com.ifs21007.dinopedia

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21007.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDinos = ArrayList<Fam>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRv.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
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
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun getListDinos(): ArrayList<Fam> {
        val dataName = resources.getStringArray(R.array.fam_name)
        val dataIcon = resources.obtainTypedArray(R.array.fam_icon)
        val dataDes = resources.getStringArray(R.array.fam_description)
        val dataPer = resources.getStringArray(R.array.fam_life_periods)
        val dataHab = resources.getStringArray(R.array.fam_habitats)
        val dataBe = resources.getStringArray(R.array.fam_classification)
        val dataCha = resources.getStringArray(R.array.fam_characteristics)
        val dataA = resources.getStringArray(R.array.sIn)
        val dataB = resources.getStringArray(R.array.eIn)

        val listDino = ArrayList<Fam>()
        for (i in dataName.indices) {
            val dino = Fam(dataIcon.getResourceId(i, -1), dataName[i],dataDes[i],dataPer[i],dataHab[i],dataBe[i],dataCha[i], dataA[i].toInt(), dataB[i].toInt())
            listDino.add(dino)
        }
        return listDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.mainRv.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.mainRv.layoutManager = LinearLayoutManager(this)
        }

        val listFamAdapter = ListFamAdapter(dataDinos)
        binding.mainRv.adapter = listFamAdapter
        listFamAdapter.setOnItemClickCallback(object : ListFamAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fam) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Fam) {
        val intentWithData = Intent(this@MainActivity, DetailMainActivity::class.java)
        intentWithData.putExtra(DetailMainActivity.EXTRA_FAM, dino)
        startActivity(intentWithData)
    }
}
