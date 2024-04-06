package com.example.elderlycare.mypage.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.elderlycare.BaseActivity
import com.example.elderlycare.MainActivity
import com.example.elderlycare.R
import com.example.elderlycare.board.ui.ListActivity
import com.example.elderlycare.databinding.ActivitySeniorMypageBinding
import com.example.elderlycare.matching.model.Caregiver
import com.example.elderlycare.matching.model.Matching
import com.example.elderlycare.matching.view.FindCaregiversActivity
import com.example.elderlycare.matching.view.FindJobsActivity
import com.google.android.material.tabs.TabLayout

class SeniorMypageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeniorMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senior_mypage)
        binding = ActivitySeniorMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                Log.d(">>", tab?.text.toString())
                when (tab?.text) {
                    "나의 정보" -> transaction.replace(R.id.tabContent, MyInfoFragment())
                    "매칭 정보" -> transaction.replace(R.id.tabContent, MatchingInfoFragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                Log.d(">>", tab?.text.toString())
                when (tab?.text) {
                    "나의 정보" -> transaction.replace(R.id.tabContent, MyInfoFragment())
                    "매칭 정보" -> transaction.replace(R.id.tabContent, MatchingInfoFragment())
                }
                transaction.commit()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                Log.d(">>", tab?.text.toString())
                when (tab?.text) {
                    "나의 정보" -> transaction.replace(R.id.tabContent, MyInfoFragment())
                    "매칭 정보" -> transaction.replace(R.id.tabContent, MatchingInfoFragment())
                }
                transaction.commit()
            }
        })
//        supportFragmentManager.beginTransaction().add(R.id.tabContent, MyInfoFragment()).commit()

        setSupportActionBar(findViewById(R.id.toolbar))
        val frgMng: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = frgMng.beginTransaction()
        val myInfoFrg: MyInfoFragment = MyInfoFragment()
        transaction.add(R.id.tabContent, myInfoFrg)
        transaction.commit()
//    twoFrg.setTextV("hello hello")
//        binding.button.setOnClickListener {
//            val xx = binding.layoutForTwofrg?.get(R.id.frgtwotxt) as TextView
//            Log.d(">>", xx.toString())
////      val frg2txt = frgMng?.findFragmentById(R.id.frgtwotxt) as TextView
////      frg2txt.text = ">> hello hello"
//        }
    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            setTitle("")
            menuInflater.inflate(R.menu.mypage_bar_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.goHome -> {startActivity(Intent(this, MainActivity::class.java))}
                R.id.goGetCare -> {startActivity(Intent(this, FindCaregiversActivity::class.java))}
                R.id.goGetJob -> {startActivity(Intent(this, FindJobsActivity::class.java))}
                R.id.goBoard -> {startActivity(Intent(this, ListActivity::class.java))}
                else -> {}
            }
            return super.onOptionsItemSelected(item)
        }













}