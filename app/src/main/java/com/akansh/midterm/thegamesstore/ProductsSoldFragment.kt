package com.akansh.midterm.thegamesstore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ProductsSoldFragment : Fragment() {
    private lateinit var root: View
    private lateinit var numberOfUsers: ImageView
    private lateinit var userNumbers: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_products_sold, container, false)

        numberOfUsers = root.findViewById(R.id.numberOfProducts)
        userNumbers = root.findViewById(R.id.productsSoldNumberText)

        return root
    }
}
