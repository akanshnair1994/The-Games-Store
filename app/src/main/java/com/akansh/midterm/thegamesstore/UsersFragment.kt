package com.akansh.midterm.thegamesstore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class UsersFragment : Fragment() {
    private lateinit var root: View
    private lateinit var numberOfProducts: ImageView
    private lateinit var productsSoldNumbers: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_users, container, false)

        numberOfProducts = root.findViewById(R.id.numberOfUsers)
        productsSoldNumbers = root.findViewById(R.id.usersNumberText)

        return root
    }
}
