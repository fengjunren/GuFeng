package cn.explo.gufeng.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import cn.explo.gufeng.enum.TriggerAction
import cn.explo.gufeng.exts.removeFragment
import cn.explo.gufeng.exts.toNextFragment
import cn.explo.gufeng.utils.ActivityDataBus
import cn.explo.gufeng.vm.SharedVM
import com.orhanobut.logger.Logger


open class BaseFragment : Fragment(),View.OnTouchListener{

    var title=""
    var is_display_back_btn=false

    open fun getFm(): FragmentManager? {
        return fragmentManager
    }
    open fun getLayoutId():Int{
        return 0
    }
    open fun initVariable(dataBinding:ViewDataBinding){

    }
    open fun initView(){

    }
    open fun initListener(){

    }
    open fun bindData(savedInstanceState: Bundle?){

    }

    open fun onVisible(triggerAction: TriggerAction){
        val instance=this
        val hasChilds=childFragmentManager!!.fragments.size>0
        if(!hasChilds)setTitleAndBackBtn()else{
            if(triggerAction==TriggerAction.onResume)
                setTitleAndBackBtn()
            else onVisibleChild()
        }
    }
    open fun onInvisible(triggerAction: TriggerAction){}

    open fun onVisibleChild() {
        val fs = childFragmentManager!!.fragments
        var visibleFrag:BaseFragment?=null
        for(f in fs){
            if(f.userVisibleHint){
                visibleFrag=f as BaseFragment
                break
            }
        }
        if (fs.size > 0&&visibleFrag==null) {
            visibleFrag = fs.last() as BaseFragment
        }
        if (fs.size > 0){
            getFm()!!.beginTransaction().show(visibleFrag!!).commitAllowingStateLoss()
            if(visibleFrag.childFragmentManager.fragments.size==0)visibleFrag.onResume()else visibleFrag.onVisibleChild()
        }
    }

    open fun setTitleAndBackBtn(){
        val instance=this

        if(!title.isEmpty()){
            val act = activity as BaseActivity
            act.supportActionBar?.title = title
            act.supportActionBar?.setDisplayHomeAsUpEnabled(is_display_back_btn)
        }
    }


    /**
     * 当fragment与viewpager、FragmentPagerAdapter一起使用时，切换页面时会调用此方法
     *
     * @param isVisibleToUser 是否对用户可见
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        val change = isVisibleToUser != userVisibleHint
        super.setUserVisibleHint(isVisibleToUser)
        // 在viewpager中，创建fragment时就会调用这个方法，但这时还没有resume，为了避免重复调用visible和invisible，
        // 只有当fragment状态是resumed并且初始化完毕后才进行visible和invisible的回调
        val instance=this
        if (isResumed && change) {
            if (userVisibleHint) {
                onVisible(TriggerAction.setUserVisibleHint)
            } else {
                onInvisible(TriggerAction.setUserVisibleHint)
            }
        }
    }

    /**
     * 当使用show/hide方法时，会触发此回调
     *
     * @param hidden fragment是否被隐藏
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        val instance=this
        if (hidden) {
            onInvisible(TriggerAction.onHiddenChanged)
        } else {
            onVisible(TriggerAction.onHiddenChanged)
        }
    }


    override fun onResume() {
        super.onResume()
        // onResume并不代表fragment可见
        // 如果是在viewpager里，就需要判断getUserVisibleHint，不在viewpager时，getUserVisibleHint默认为true
        // 如果是其它情况，就通过isHidden判断，因为show/hide时会改变isHidden的状态
        // 所以，只有当fragment原来是可见状态时，进入onResume就回调onVisible
//        var parentUserVisibleHint=true
        val instance=this
        val parent=this.parentFragment
        val _isVisible=if(parent==null){
            userVisibleHint && !isHidden
        }else{
            val grandpa=parent.parentFragment
            val _visible=if(grandpa==null){
                parent.userVisibleHint&&parent.isVisible&&userVisibleHint && !isHidden
            }else{
                grandpa.userVisibleHint&&grandpa.isVisible&&parent.userVisibleHint&&parent.isVisible&&userVisibleHint && !isHidden
            }
            _visible
        }
        if (_isVisible) {
            onVisible(TriggerAction.onResume)
        }
    }

    override fun onPause() {
        super.onPause()
        // onPause时也需要判断，如果当前fragment在viewpager中不可见，就已经回调过了，onPause时也就不需要再次回调onInvisible了
        // 所以，只有当fragment是可见状态时进入onPause才加调onInvisible
        if (userVisibleHint && !isHidden) {
            onInvisible(TriggerAction.onPause)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.i("onCreateView")
        val dataBinding : ViewDataBinding= DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        dataBinding.setLifecycleOwner(this)
        initVariable(dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.i("onActivityCreated")
        initView()
        initListener()
        bindData(savedInstanceState)
    }


    inline  fun  <reified T: ViewModel> getViewModel():T=ViewModelProviders.of(this).get(T::class.java)

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true
    }


    fun showNextFragment(outFrag: Fragment?, infrag: Fragment, isStack: Boolean){
        toNextFragment(getFm(),getFragmentContainId(),outFrag,infrag,isStack)
    }

    fun showNextFragment(outFrag: Fragment?, infrag: Fragment){
        showNextFragment(outFrag,infrag,true)
    }


   open fun getFragmentContainId():Int=0

    fun finishFragment(fragment: Fragment){
        removeFragment(fragment,getFm())
    }

}