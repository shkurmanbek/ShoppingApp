package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CategoryAdapter
import com.example.myapplication.model.Category
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    var button: Button?= null
    var textView: TextView?= null
    lateinit var preferences: SharedPreferences
    var isremembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button=findViewById(R.id.button)
        textView=findViewById(R.id.textView2)
        preferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val mail = preferences.getString("MAIL", " ").toString()
        isremembered = preferences.getBoolean("CHECKBOX", false)

        button?.setOnClickListener{
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            val countString=textView?.text.toString()
            intent.putExtra("total", countString)
            startActivity(intent)
        }

        if(isremembered){
            button?.setText(mail)
        }


        val search = findViewById<TextView>(R.id.main_search)
        val shapeable = findViewById<ShapeableImageView>(R.id.main_shapeable)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)

        shapeable.shapeAppearanceModel = ShapeAppearanceModel().toBuilder().setAllCorners(CornerFamily.ROUNDED, 20F).build()
        val itemList = ArrayList<Category>()

        val adapter = CategoryAdapter(itemList)

        setupRecyclerView(adapter,recyclerView)

        val db = FirebaseDatabase.getInstance().reference
        val valueEventListener= object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                for(i in snapshot.children){
                    itemList.add(Category(i.key.toString(), i.child("image").getValue().toString()))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error !", Toast.LENGTH_SHORT).show()
            }
        }
        db.child("category").addListenerForSingleValueEvent(valueEventListener)
    }
    fun setupRecyclerView(adapter: CategoryAdapter, recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =adapter
    }



}