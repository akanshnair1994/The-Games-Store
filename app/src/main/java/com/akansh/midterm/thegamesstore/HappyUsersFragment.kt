package com.akansh.midterm.thegamesstore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class HappyUsersFragment : Fragment() {
    private lateinit var root: View
    private lateinit var numberOfHappyUsers: ImageView
    private lateinit var happyUserNumbers: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_happy_users, container, false)

        numberOfHappyUsers = root.findViewById(R.id.numberOfHappyUsers)
        happyUserNumbers = root.findViewById(R.id.happyUsersNumberText)

        return root
    }
}
