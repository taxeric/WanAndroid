package com.eric.wanandroid.utils

import androidx.fragment.app.FragmentManager
import com.eric.wanandroid.base.ui.BaseFragment

/**
 * Created by eric on 20-9-22
 */
class ActivityUtils {

    companion object{

        /**
         * 把一个fragment添加到activity
         */
        fun addFragmentToActivity(
                fragmentManager: FragmentManager,
                fragment: BaseFragment,
                tag: String,
                resID: Int
        ){
            val transaction = fragmentManager.beginTransaction()
            transaction.add(resID, fragment, tag).show(fragment)
            transaction.commit()
        }

        /**
         * 切换fragment
         */
        fun switchFragment(
                fragmentManager: FragmentManager,
                curFragment: BaseFragment,
                toFragment: BaseFragment,
                toTag: String,
                resID: Int
        ){
            if (curFragment != toFragment){
                val transacion = fragmentManager.beginTransaction()
                if (toFragment.isAdded){
                    transacion.hide(curFragment).show(toFragment)
                } else {
                    transacion.hide(curFragment).add(resID, toFragment, toTag).show(toFragment)
                }
                transacion.commit()
            }
        }
    }
}