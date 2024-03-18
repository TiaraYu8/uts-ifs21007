package com.ifs21007.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21007.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFam = ArrayList<Fam>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvYogies.setHasFixedSize(false)
        dataFam.addAll(getListFruits())
        showRecyclerList()
    }
    @SuppressLint("Recycle")
    private fun getListFruits(): ArrayList<Fam> {
        val dataName =
            resources.getStringArray(R.array.fam_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.fam_icon)
        val dataDes =
            resources.getStringArray(R.array.fam_description)
        val dataCharac =
            resources.getStringArray(R.array.fam_characteristics)
        val dataMentalBenefits =
            resources.getStringArray(R.array.fam_habitats)
        val dataDesPose =
            resources.getStringArray(R.array.fam_classification)
        val dataDesBehav =
            resources.getStringArray(R.array.fam_life_periods)
        val listPose = ArrayList<Fam>()
        for (i in dataName.indices) {
            val fam = Fam(dataName[i],
                dataIcon.getResourceId(i, -1), dataDes[i],
                dataCharac[i], dataMentalBenefits[i], dataDesPose[i], dataDesBehav[i],)
            listPose.add(fam)
        }
        return listPose
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvYogies.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvYogies.layoutManager =
                LinearLayoutManager(this)
        }
        val listPoseAdapter = ListPoseAdapter(dataFam)
        binding.rvYogies.adapter = listPoseAdapter
        listPoseAdapter.setOnItemClickCallback(object :
            ListPoseAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fam) {
                showSelectedPose(data)
            }
        })
    }
    private fun showSelectedPose(fam: Fam) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAM, fam)
        startActivity(intentWithData)
    }
}
