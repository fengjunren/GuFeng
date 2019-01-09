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
import cn.explo.gufeng.exts.removeFragment
import cn.explo.gufeng.exts.toNextFragment
import cn.explo.gufeng.utils.ActivityDataBus
import cn.explo.gufeng.vm.SharedVM
import com.orhanobut.logger.Logger


open class BaseFragment : Fragment(),View.OnTouchListener{

//    private val STATE_SAVE_IS_USER_VISIBLE_HINT = "STATE_SAVE_IS_USER_VISIBLE_HINT"
//    private val STATE_SAVE_TITLE="STATE_SAVE_TITLE"
//    private val STATE_SAVE_IS_DISPLAY_BACK_BTN="STATE_SAVE_IS_DISPLAY_BACK_BTN"
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
    open fun bindData(){

    }

    open fun onVisible(){
        val instance=this
        setTitleAndBackBtn()
    }
    open fun onInvisible(){}

    open fun setTitleAndBackBtn(){
        if(!title.isEmpty()){
            ActivityDataBus.getData(activity, SharedVM::class.java).setTitle(title)
            ActivityDataBus.getData(activity, SharedVM::class.java).setIsDisplayBackBtn(is_display_back_btn)
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
                onVisible()
            } else {
                onInvisible()
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
            onInvisible()
        } else {
            onVisible()
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
            parent.userVisibleHint&&userVisibleHint && !isHidden
        }
        if (_isVisible) {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        // onPause时也需要判断，如果当前fragment在viewpager中不可见，就已经回调过了，onPause时也就不需要再次回调onInvisible了
        // 所以，只有当fragment是可见状态时进入onPause才加调onInvisible
        if (userVisibleHint && !isHidden) {
            onInvisible()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
//            val is_user_visible_hint = savedInstanceState.getBoolean(STATE_SAVE_IS_USER_VISIBLE_HINT)
             val instance=this
//            Logger.i("onCreate savedInstanceState:"+instance::class.java.simpleName+",is_user_visible_hint:"+is_user_visible_hint)
//            is_display_back_btn=savedInstanceState.getBoolean(STATE_SAVE_IS_DISPLAY_BACK_BTN)
//            title=savedInstanceState.getString(STATE_SAVE_TITLE)
//            userVisibleHint=is_user_visible_hint
//            Logger.i("is_user_visible_hint:$is_user_visible_hint ,is_display_back_btn:$is_display_back_btn,title:$title --------------------")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Logger.i("onSaveInstanceState  STATE_SAVE_IS_HIDDEN:$isHidden --------------------")
        val instance=this
//        outState.putBoolean(STATE_SAVE_IS_USER_VISIBLE_HINT, userVisibleHint)
//        outState.putString(STATE_SAVE_TITLE,title)
//        outState.putBoolean(STATE_SAVE_IS_DISPLAY_BACK_BTN,is_display_back_btn)
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
        bindData()
    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if(isVisibleToUser&&activity!=null&&!title.isEmpty()){
//            ActivityDataBus.getData(activity, SharedVM::class.java).setTitle(title)
//            ActivityDataBus.getData(activity, SharedVM::class.java).setIsDisplayBackBtn(is_display_back_btn)
//        }
//        onSetUserVisibleHint(isVisibleToUser)
//    }
//
//    open fun onSetUserVisibleHint(isVisibleToUser: Boolean){
//
//    }


    inline  fun  <reified T: ViewModel> getViewModel():T=ViewModelProviders.of(this).get(T::class.java)

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }


    fun showNextFragment(outFrag: Fragment?, infrag: Fragment, isStack: Boolean){
        toNextFragment(getFm(),getFragmentContainId(),outFrag,infrag,isStack)
    }

    fun showNextFragment(outFrag: Fragment?, infrag: Fragment){
        showNextFragment(outFrag,infrag,true)
    }

//    fun showNextFragment(outTag: String?, infrag: Fragment, isStack: Boolean){
//        toNextFragment(getFm(),getFragmentContainId(),outTag,infrag,isStack)
//    }
//
//    fun showNextFragment(outTag: String?, infrag: Fragment){
//        showNextFragment(outTag,infrag,true)
//    }


//    fun toNextFragment(outTag: String?, infrag: Fragment, isStack: Boolean) {
//        //        FragmentManager fm=getChildFragmentManager();
//        var ta: FragmentTransaction? = null
//        val fm=getFm()
//        if (outTag != null && !outTag.isEmpty()) {
//            val outFrag = fm?.findFragmentByTag(outTag)
//            if (outFrag != null) {
//                ta = fm.beginTransaction()
//                ta!!.hide(outFrag!!).commitAllowingStateLoss()
//            }
//        }
//
//        val inTag = infrag.javaClass.name
//        val inFragtemp = fm?.findFragmentByTag(inTag)
//        ta = fm?.beginTransaction()
//        if (inFragtemp != null) {
//            ta!!.show(inFragtemp!!)
//        } else {
//            if (isStack) ta!!.addToBackStack(inTag)
//            ta?.add(getFragmentContainId(), infrag)
//        }
//        // Store the Fragment in stack
//        ta?.commit()
//    }
//
//    fun toNextFragment(outTag: String, infrag: Fragment) {
//        toNextFragment(outTag, infrag, true)
//    }

   open fun getFragmentContainId():Int=0

    fun finishFragment(fragment: Fragment){
        removeFragment(fragment,getFm())
    }

}