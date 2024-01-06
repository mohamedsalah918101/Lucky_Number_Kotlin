package com.petra.luckynumber

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.FragmentTransaction

class secondFragment : Fragment() {

    private lateinit var vv_video: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        //video
        vv_video=view.findViewById(R.id.vv_video)
        val uri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.fire}")
        vv_video.setVideoURI(uri)
        vv_video.requestFocus()
        vv_video.start()

        // Recieve InputNumber, RandomNumber and ClickCount From first Fragment
        val data = arguments
        val InputNumberFromFirstFragment : Int = data?.getInt("InputNumberToSecondFragment", 1).toString().toInt()
        val RandomNumberFromFirstFragment : Int = data?.getInt("RandomNumberToSecondFragment",1).toString().toInt()
        val clickCountFromFirstFragment :Int = data?.getInt("clickCountToSecondFragment",1).toString().toInt()
        val congratsTextView: TextView = view.findViewById(R.id.congrats)
        congratsTextView.text = " Congratulations! You Won after ${clickCountFromFirstFragment} times"

        //Buttons
        val playAgain : Button = view.findViewById(R.id.playAgain)
        val logout : Button = view.findViewById(R.id.logout)

        playAgain.setOnClickListener()
        {
            // Move To First Fragment
            val firstFragment : Fragment = firstFragment()
            val InputNumberToFirstFragment :Int = InputNumberFromFirstFragment
            val RandomNumberToFirstFragment:Int = RandomNumberFromFirstFragment
            val bundle = Bundle()
            bundle.putInt("InputNumberToFirstFragment", InputNumberToFirstFragment)
            bundle.putInt("RandomNumberToFirstFragment", RandomNumberToFirstFragment)
            firstFragment.arguments = bundle
            val fragmentTransaction : FragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentLayout,firstFragment).commit()
        }

        logout.setOnClickListener()
        {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}