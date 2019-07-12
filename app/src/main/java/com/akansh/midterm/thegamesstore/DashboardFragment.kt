package com.akansh.midterm.thegamesstore

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DashboardFragment : Fragment() {
    private lateinit var root: View
    private lateinit var pager: ViewPager
    private lateinit var swipeAdapter: SwipeAdapter
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        textView1 = root.findViewById(R.id.textView1)
        textView2 = root.findViewById(R.id.textView2)
        textView3 = root.findViewById(R.id.textView3)
        pager = root.findViewById(R.id.pager)
        swipeAdapter = SwipeAdapter(fragmentManager!!)
        pager.adapter = swipeAdapter

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {

            }

            override fun onPageScrolled(position: Int, p1: Float, p2: Int) {
                when (position) {
                    0 -> {
                        textView1.setBackgroundResource(R.drawable.text_view_background)
                        textView2.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView3.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
                        textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                        textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                    }
                    1 -> {
                        textView1.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView2.setBackgroundResource(R.drawable.text_view_background)
                        textView3.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                        textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
                        textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                    }
                    2 -> {
                        textView1.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView2.setBackgroundResource(R.drawable.text_view_background_dark)
                        textView3.setBackgroundResource(R.drawable.text_view_background)
                        textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                        textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
                        textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
                    }
                }
            }

            override fun onPageSelected(p0: Int) {

            }
        })

        textView1.setOnClickListener {
            textView1.setBackgroundResource(R.drawable.text_view_background)
            textView2.setBackgroundResource(R.drawable.text_view_background_dark)
            textView3.setBackgroundResource(R.drawable.text_view_background_dark)
            pager.setCurrentItem(0, true)
            textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
            textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
            textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
        }

        textView2.setOnClickListener {
            textView1.setBackgroundResource(R.drawable.text_view_background_dark)
            textView2.setBackgroundResource(R.drawable.text_view_background)
            textView3.setBackgroundResource(R.drawable.text_view_background_dark)
            pager.setCurrentItem(1, true)
            textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
            textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
            textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
        }

        textView3.setOnClickListener {
            textView1.setBackgroundResource(R.drawable.text_view_background_dark)
            textView2.setBackgroundResource(R.drawable.text_view_background_dark)
            textView3.setBackgroundResource(R.drawable.text_view_background)
            pager.setCurrentItem(2, true)
            textView1.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
            textView2.setTextColor(ContextCompat.getColor(root.context, android.R.color.white))
            textView3.setTextColor(ContextCompat.getColor(root.context, android.R.color.black))
        }

        return root
    }
}
