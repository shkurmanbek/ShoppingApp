package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nex3z.notificationbadge.NotificationBadge
import org.w3c.dom.Text

class New : AppCompatActivity(){
    lateinit var preferences: SharedPreferences

    lateinit var toggle: ActionBarDrawerToggle

    var textPref:TextView?=null
    var buttonLogOut:Button?=null
    var buttonBack:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        val navigationView:NavigationView=findViewById(R.id.navigation)

        toggle=ActionBarDrawerToggle(this,drawerLayout,0,0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_set -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_log -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            }
            true
        }
        val headerView : View = navigationView.getHeaderView(0)
        val navUserName : TextView = headerView.findViewById(R.id.nav_user)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener() {
            when(it.itemId){
                R.id.navigation_home -> {
                    val intent = Intent(this@New, MainActivity::class.java)
                    val editor: SharedPreferences.Editor=preferences.edit()
                    editor.apply()
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_cart -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.navigation_profile -> Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
            }
            true
        }
        val notificationBadge :NotificationBadge = findViewById(R.id.badge)
        notificationBadge.setText("12",true)

        textPref=findViewById(R.id.textPref)
        buttonLogOut=findViewById(R.id.logout_btn)

        preferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val mail = preferences.getString("MAIL", " ").toString()
        val isremembered = preferences.getBoolean("CHECKBOX", false)

        textPref?.text="$mail, Hello!"
        Log.d("MAIL???????", textPref?.text.toString())

        navUserName.setText(mail)
        buttonLogOut?.setText("$mail, Log Out")



        buttonLogOut?.setOnClickListener(){
            val editor:SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this@New, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }


        buttonBack=findViewById(R.id.back_btn)
        buttonBack?.setOnClickListener(){
            if(isremembered){
                val intent = Intent(this@New, MainActivity::class.java)
                val editor: SharedPreferences.Editor=preferences.edit()
                editor.putString("MAIL", mail)
                editor.apply()
                startActivity(intent)
                finish()
                Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
            }else{
                val editor:SharedPreferences.Editor = preferences.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(this@New, MainActivity::class.java)
                startActivity(intent)
                finish()}

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        else return super.onOptionsItemSelected(item)
    }
}