package com.dygak.storingdata

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dygak.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //SharedPreferences : veriyi kaydedeceğimiz yapının adı. Aslında bir XML dosyasıdır. modeprivate sadece benim uygulamam ulaşsın demek için kullandık
         sharedPref = this.getSharedPreferences("com.dygak.storingdata", MODE_PRIVATE)

        val userAgePref = sharedPref.getInt("age",-1)

        if(userAgePref == -1){
            binding.textView.text = "Your age: "
        }else{
            binding.textView.text = "Your age: ${userAgePref}"
        }

    }

    fun save(view: View){
        val myAge = binding.editTextText.text.toString().toIntOrNull()

        if (myAge != null){
            binding.textView.text = "Your age: ${myAge}"
            sharedPref.edit().putInt("age",myAge).apply()
        }
    }

    fun delete(view: View){
        sharedPref.edit().remove("age").apply()
    }

}