package com.example.letsshare.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.letsshare.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.first_sem.setOnClickListener {
            Log.d("main","clicked1")
        }
        root.second_sem.setOnClickListener {
            Log.d("main","clicked2")
        }
        root.third_sem.setOnClickListener {
            Log.d("main","clicked3")
        }
        root.fourth_sem.setOnClickListener {
            Log.d("main","clicked4")
        }
        root.fifth_sem.setOnClickListener {
            Log.d("main","clicked5")
        }
        root.sixth_sem.setOnClickListener {
            Log.d("main","clicked6")
        }
        root.seventh_sem.setOnClickListener {
            Log.d("main","clicked7")
        }
        root.eighth_sem.setOnClickListener {
            Log.d("main","clicked8")
        }

        return root
    }
}