package com.example.letsshare.ui.home

import android.content.Intent
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
import com.example.letsshare.book_activity
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
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","first")
            startActivity(ints)
        }
        root.second_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","second")
            startActivity(ints)
        }
        root.third_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","third")
            startActivity(ints)
        }
        root.fourth_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","fourth")
            startActivity(ints)
        }
        root.fifth_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","fifth")
            startActivity(ints)
        }
        root.sixth_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","sixth")
            startActivity(ints)
        }
        root.seventh_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","seventh")
            startActivity(ints)
        }
        root.eighth_sem.setOnClickListener {
            val ints=Intent(context,book_activity::class.java)
            ints.putExtra("sem","eight")
            startActivity(ints)
        }

        return root
    }
}