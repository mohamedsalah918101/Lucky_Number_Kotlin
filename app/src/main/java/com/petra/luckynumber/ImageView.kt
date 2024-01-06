package com.petra.luckynumber

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class ImageView : AppCompatActivity() {
    private lateinit var cameraIntent: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        val name: TextView = findViewById(R.id.name)
        val inputnumber: TextView = findViewById(R.id.inputnumber)

        val name_str:String = intent.getStringExtra("name").toString()
        val input_num:Int = intent.getIntExtra("inputnumber",1).toString().toInt()

        name.text="Hello ${name_str}"
        inputnumber.text="Favorite number is ${input_num}"

        // Permission
        val image_open_gallery: ImageView = findViewById(R.id.image_open_gallery)
        cameraIntent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode== RESULT_OK){
                Toast.makeText(this,"Picture Updated",Toast.LENGTH_LONG).show()
            }
        }
        image_open_gallery.setOnClickListener(){
            cameraIntent.launch(
                Intent("android.media.action.IMAGE_CAPTURE")
            )
            if(ActivityCompat.checkSelfPermission(this@ImageView, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this@ImageView, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }
            else
            {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,1)
            }
        }

        val play : ImageView = findViewById(R.id.dice)
        play.setOnClickListener(){
            // Move To First Fragment
            val firstFragment : Fragment = firstFragment()
            val InputNumberToFirstFragment :Int = input_num
            val bundle = Bundle()
            bundle.putInt("InputNumberToFirstFragment", InputNumberToFirstFragment)
            firstFragment.arguments = bundle
            val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentLayout,firstFragment).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK)
        {
            if(requestCode==100)
            {
                Toast.makeText(this@ImageView,"Picture Updated", Toast.LENGTH_LONG).show()
            }
        }
    }
}