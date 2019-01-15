package cn.explo.gufeng.exts

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

fun toNextFragment(fm:FragmentManager?,fragContainId:Int,outTag: String?, infrag: Fragment, isStack: Boolean) {
    var ta: FragmentTransaction?
    if (outTag != null && !outTag.isEmpty()) {
        var outFrag = fm?.findFragmentByTag(outTag)
       if (outFrag == null) {
            for (f in fm!!.fragments){
                if(f.javaClass.name == outTag){
                    outFrag=f
                    break
                }
            }
        }
        if (outFrag != null) {
            ta = fm!!.beginTransaction()
            ta?.hide(outFrag).commitAllowingStateLoss()
        }
    }

    val inTag = infrag.javaClass.name
    val inFragtemp = fm?.findFragmentByTag(inTag)
    ta = fm?.beginTransaction()
    if (inFragtemp != null) {
        ta?.show(inFragtemp)
    } else {
        if (isStack) ta!!.addToBackStack(inTag)
        ta?.add(fragContainId, infrag)
    }
    // Store the Fragment in stack
    ta?.commit()
}

fun toNextFragment(fm:FragmentManager,fragContainId:Int,outTag: String?, infrag: Fragment) {
    toNextFragment(fm,fragContainId,outTag,infrag,true)
}

fun toNextFragment(fm:FragmentManager?,fragContainId:Int,outfrag: Fragment?, infrag: Fragment, isStack: Boolean) {
    var ta: FragmentTransaction?= fm?.beginTransaction()
    if (outfrag != null) {
        ta?.hide(outfrag)?.commitAllowingStateLoss()
    }
    val inTag = infrag?.javaClass?.name
    val inFragtemp = fm?.findFragmentByTag(inTag)
    ta = fm?.beginTransaction()
    if (inFragtemp != null) {
        ta?.show(inFragtemp)
    } else {
        if (isStack) ta?.addToBackStack(inTag)
       ta?.add(fragContainId, infrag)
    }
    // Store the Fragment in stack
    ta?.commitAllowingStateLoss()
}
fun toNextFragment(fm:FragmentManager,fragContainId:Int,outfrag: Fragment?, infrag: Fragment) {
    toNextFragment(fm,fragContainId,outfrag,infrag,true)
}



/**
 * Destroy self.
 */
fun removeFragment(fragment: Fragment,fManager:FragmentManager?) {
    fManager
        ?.beginTransaction()
        ?.remove(fragment)
        ?.commitAllowingStateLoss()
}