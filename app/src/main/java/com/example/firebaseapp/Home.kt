package com.example.firebaseapp

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Home : AppCompatActivity() {

    private lateinit var tab_layout: TabLayout
    private lateinit var view_pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tab_layout = findViewById(R.id.tab_layout)
        view_pager = findViewById(R.id.view_pager)
        view_pager.adapter = Adapter(this)
        //Tab del layout y posicion del view
        TabLayoutMediator(tab_layout, view_pager){ tab, index ->
            tab.text = when(index){
            0 -> {"Rewards"}
            1 -> {"Home"}
            2 -> {"Profile" }
            else -> {throw Resources.NotFoundException("Posicion no encontrada")}
            }
        }.attach()
    }
}