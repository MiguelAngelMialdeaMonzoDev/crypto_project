package com.example.crypto_project.helpers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.crypto_project.R
import java.lang.ref.WeakReference
import java.util.*

class NavigationHelper {

    companion object {
        const val F_GAMES = "F_GAMES"
        const val F_PLAYS = "F_PLAYS"
        const val F_RESULTS = "F_RESULTS"
        const val F_REGISTER_FIRST = "F_REGISTER_FIRST"
        const val F_REGISTER_SECOND = "F_REGISTER_SECOND"
        const val F_REGISTER_THIRD = "F_REGISTER_THIRD"
    }

    private val stack = mutableListOf<WeakReference<Fragment>>()

    /*fun showFragment(
        activity: AppCompatActivity,
        currentFragment: Fragment?,
        futureFragment: Fragment,
        tag: String
    ): Fragment {

        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (tag == F_GAMES || tag == F_PLAYS || tag == F_RESULTS || tag == F_REGISTER_FIRST || tag == F_REGISTER_SECOND || tag == F_REGISTER_THIRD) {
            clearStack()
            if (fragmentManager.findFragmentByTag(tag) != null) {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                    Objects.requireNonNull(
                        fragmentManager.findFragmentByTag(
                            tag
                        )
                    ).let {
                        it?.let { it1 ->
                            fragmentTransaction.show(
                                it1
                            )
                        }
                    }
                }
            } else {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                }
                fragmentTransaction.add(R.id.main_content, futureFragment, tag)
            }
        } else {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment)
                stack.add(WeakReference(currentFragment))
            }
            fragmentTransaction.add(R.id.main_content, futureFragment, tag)
        }

        fragmentTransaction
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        return futureFragment
    }*/

    fun backStackFragment(activity: AppCompatActivity, currentFragment: Fragment?): Fragment? {

        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var lastFragment: Fragment? = null

        if (stack.isEmpty()) {
            activity.finish()
        } else {
            lastFragment = fragmentManager.findFragmentByTag(stack[stack.size - 1].get()?.tag)
            if (lastFragment != null) {
                fragmentTransaction.remove(currentFragment!!)
                fragmentTransaction.show(Objects.requireNonNull(lastFragment))
                fragmentTransaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .commit()
                stack.removeAt(stack.size - 1)
            }
        }
        return lastFragment
    }

    private fun clearStack() {
        val iterator = stack.iterator()

        while (iterator.hasNext()) {
            val fragment = iterator.next().get()
            if (fragment != null)
                if (fragment.tag != null)
                    iterator.remove()
        }
    }
}