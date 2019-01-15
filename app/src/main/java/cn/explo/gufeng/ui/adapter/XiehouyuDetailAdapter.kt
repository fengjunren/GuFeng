package cn.explo.gufeng.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.explo.gufeng.ui.fragment.XiehouyuDetailFrag

class XiehouyuDetailAdapter(fm: FragmentManager?, total:Int) : FragmentStatePagerAdapter(fm) {
    private val mTotal=total

    override fun getItem(pos: Int): Fragment {
        val args= Bundle().apply {
            putInt("id",pos+1)
        }
        return XiehouyuDetailFrag().apply {
            arguments=args
        }
    }

    override fun getCount(): Int {
         return mTotal
    }



}