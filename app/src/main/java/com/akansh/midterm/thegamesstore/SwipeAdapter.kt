package com.akansh.midterm.thegamesstore

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SwipeAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        if (position == 1)
            return UsersFragment()
        else if (position == 2)
            return ProductsSoldFragment()
        else
            return HappyUsersFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}
