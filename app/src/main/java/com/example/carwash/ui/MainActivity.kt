package com.example.carwash.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.carwash.R
import com.example.carwash.ui.fragments.AgendarLimpezaFragment
import com.example.carwash.ui.fragments.EditAccountFragment
import com.example.carwash.ui.fragments.LoginFragment
import com.example.carwash.ui.fragments.MeusVeiculosFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.nav_drawer_layout)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,"Open", "Close")
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        }


    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.meusVeiculos -> {
                val intent = Intent(this, MeusVeiculosFragment::class.java)
                startActivity(intent)
            }
            R.id.agendarLimpeza -> {
                val intent = Intent(this, AgendarLimpezaFragment::class.java)
                startActivity(intent)
            }
            R.id.status -> {
                val intent = Intent(this, AgendarLimpezaFragment::class.java)
                startActivity(intent)
            }
            R.id.configuracoes -> {
                val intent = Intent(this, EditAccountFragment::class.java)
                startActivity(intent)
            }
            R.id.sair -> {
                val intent = Intent(this, LoginFragment::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    }
}