package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2: AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    var textView: TextView?=null
    var name: EditText?=null
    var email: EditText?=null
    var password: EditText?=null
    var saveButton: Button?=null
    var buttonRegister: Button?=null
    var signinButton: Button?=null
    var login_password: EditText?=null
    var login_mail: EditText?=null
    var registration_layout: LinearLayout?=null
    var layout_new: LinearLayout?=null
    var checkBox: CheckBox?=null

    lateinit var sharedPreferences: SharedPreferences
    var isremembered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newxml)

        dbHelper = DBHelper(this)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isremembered = sharedPreferences.getBoolean("CHECKBOX", false)
        if(isremembered){
            val intent = Intent(this@MainActivity2, New::class.java)
            startActivity(intent)
            Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
        }
        registration_layout=findViewById(R.id.registration_layout)
        layout_new=findViewById(R.id.layout_new)
        textView=findViewById(R.id.text_view)
        buttonRegister=findViewById(R.id.buttonRegister)
        signinButton=findViewById(R.id.signin_btn)
        saveButton=findViewById(R.id.save_btn)
        password=findViewById(R.id.password)
        email=findViewById(R.id.email)
        name=findViewById(R.id.name)
        login_mail=findViewById(R.id.login_mail)
        login_password=findViewById(R.id.login_password)
        checkBox=findViewById(R.id.checkBox)

        var total=""

        total = intent.extras?.getString("total").toString()
        textView?.text=total

        saveButton?.setOnClickListener(){
            dbHelper.insertUserData(name?.text.toString(), email?.text.toString(), password?.text.toString())
            showLogin()
        }

        buttonRegister?.setOnClickListener(){
            showRegistration()
        }

        signinButton?.setOnClickListener(){
            if (dbHelper.userIsInDB(login_mail?.text.toString(), login_password?.text.toString())){
                Toast.makeText(this, "KEK", Toast.LENGTH_SHORT).show()
            }
            else{
                val usermail = login_mail?.text.toString()
                Log.d("INFO???????????", usermail)

                val checked: Boolean? = checkBox?.isChecked
                val editor: SharedPreferences.Editor=sharedPreferences.edit()
                editor.putString("MAIL", usermail)
                editor.putBoolean("CHECKBOX", checked!!)
                editor.apply()

                val intent = Intent(this@MainActivity2, New::class.java)
                intent.putExtra("usermail", usermail)
                startActivity(intent)
                Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun showRegistration(){
        layout_new?.visibility= View.INVISIBLE
        registration_layout?.visibility= View.VISIBLE
    }
    fun showLogin(){
        layout_new?.visibility= View.VISIBLE
        registration_layout?.visibility= View.INVISIBLE
    }
}