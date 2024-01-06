package com.petra.luckynumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlin.random.Random
import kotlin.random.nextInt


class firstFragment : Fragment() {

    // Click Counter
    var clickCount:Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val imageView : ImageView = view.findViewById(R.id.imageView)
        val randomNumberTextView: TextView = view.findViewById(R.id.randomNumber)
        // Random Number
        val number = Random.nextInt(1..6)
        randomNumberTextView.text = "Lucky Number = ${number.toString().toInt()}"

        // Recieve input Number from ImageView
        val data = arguments
        val InputNumberFromImageView :Int = data?.getInt("InputNumberToFirstFragment",1).toString().toInt()

        imageView.setOnClickListener(){
            if (InputNumberFromImageView == number) {
                // Move To Second Fragment
                val secondFragment : Fragment = secondFragment()
                val InputNumberToSecondFragment :Int = InputNumberFromImageView
                val bundle = Bundle()
                bundle.putInt("InputNumberToSecondFragment", InputNumberToSecondFragment)
                bundle.putInt("RandomNumberToSecondFragment", number)
                secondFragment.arguments = bundle
                val fragmentTransaction : FragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentLayout,secondFragment).commit()
            }
            else {
                // Change the Random number
                val randomNumber = generateRandomNumber()
                randomNumberTextView.text = "Lucky Number = ${randomNumber.toString().toInt()}"
                val howManyClicks = generateClickCounter()
                val equalNumber = randomNumber
                if (InputNumberFromImageView == equalNumber){
                    val secondFragment : Fragment = secondFragment()
                    val InputNumberToSecondFragment :Int = InputNumberFromImageView
                    val bundle = Bundle()
                    bundle.putInt("InputNumberToSecondFragment", InputNumberToSecondFragment)
                    bundle.putInt("RandomNumberToSecondFragment", number)
                    val clickCountToSecondFragment = howManyClicks
                    bundle.putInt("clickCountToSecondFragment",clickCountToSecondFragment)
                    secondFragment.arguments = bundle
                    val fragmentTransaction : FragmentTransaction = parentFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentLayout,secondFragment).commit()
                }
            }
        }

        return view
    }
    private fun generateRandomNumber(): Int {
        return Random.nextInt(1..6) // Generates random number between 1 and 6 (inclusive)
    }
    private fun generateClickCounter(): Int {
        return clickCount++ // Generates how many times the user clicked on the button
    }


}
