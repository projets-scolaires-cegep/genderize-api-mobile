package com.example.genderize_exemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val btn = findViewById<Button>(R.id.btnGO)
            btn.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val nom = findViewById<EditText>(R.id.textName).text
            val url = "https://api.genderize.io?name=$nom"
            val r = JsonObjectRequest (
                Request.Method.GET, //Méthode GET, PUT, POST, DELETE, etc.
                url, //url de la ressource
                null,
                {
                    findViewById<TextView>(R.id.textGenre).text = it.getString("gender")
                    findViewById<TextView>(R.id.textProb).text = (it.getDouble("probability")*100).toString()+"%"
                    //Traiter la réponse (it est un JSONObject)
                },
                Response.ErrorListener {
                    //Traiter la réponse lorsqu'une erreur se produit (it contient erreur)
                })
            queue.add(r)

        }
    }
}