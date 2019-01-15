package cn.explo.gufeng.ui.fragment

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.base.MultiTypeAdapter
import cn.explo.gufeng.databinding.DengmiLstFragBinding
import cn.explo.gufeng.entity.DengMi
import cn.explo.gufeng.vm.DengMiVM
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView


/**
 * A simple [Fragment] subclass.
 *
 */
class DengmiLstFrag : BaseNestFragment() {
    private lateinit var  dBinding : DengmiLstFragBinding

    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView: SwipeMenuRecyclerView
    private lateinit var mAdapter: MultiTypeAdapter
    private  lateinit var mDataList:  List<DengMi>
    private lateinit var mDengMiVM:DengMiVM
    override fun getLayoutId(): Int {
        return R.layout.dengmi_lst_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding= dataBinding as DengmiLstFragBinding
        title="灯谜"
        mDengMiVM=getViewModel()
        mDataList= arrayListOf()
    }




    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        mRefreshLayout = dBinding.refreshLayout
        mRefreshLayout.setOnRefreshListener(mRefreshListener) // 刷新监听。

        mRecyclerView = dBinding.recyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
//        mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
//        mRecyclerView.addItemDecoration(DefaultItemDecoration(ContextCompat.getColor(context!!, R.color.divider_color)))
        mRecyclerView.setSwipeItemClickListener(mItemClickListener) // RecyclerView Item点击监听。

        mRecyclerView.useDefaultLoadMore() // 使用默认的加载更多的View。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener) // 加载更多的监听。

//        mAdapter = DengmiLstAdapter(activity)
        mAdapter=MultiTypeAdapter()
        mRecyclerView.adapter = mAdapter

        // 请求服务器加载数据。
        loadData(0)
    }


    /**
     * 刷新。
     */
    private val mRefreshListener = SwipeRefreshLayout.OnRefreshListener {
//        mRecyclerView.postDelayed(Runnable { loadData() }, 1000) // 延时模拟请求服务器。
        loadData(0)
    }

    /**
     * 加载更多。
     */
    private val mLoadMoreListener = SwipeMenuRecyclerView.LoadMoreListener {

        val startId=mDataList.last().id
        loadData(startId)
//        mDengMiVM.qryNextPage(startId).observe(this, Observer {
//            mDataList=mDataList.plus(it!!)
//            mAdapter.notifyDataSetChanged(mDataList)
//            val dataEmpty=if(it.isEmpty())true else false
//            val hasMore=if(it.size<10)false else true
//            mRecyclerView.loadMoreFinish(dataEmpty, hasMore)
//        })

    }

    /**
     * Item点击监听。
     */
    private val mItemClickListener = SwipeItemClickListener { itemView, position ->
        val parent=parentFragment as NestContainFragment
        val args= Bundle().apply {
            putInt("position",position)
        }
        val destFrag=DengmiContainFrag().apply {
            arguments=args
        }

        parent.showNextFragment(this@DengmiLstFrag,destFrag)
    }

    private fun loadData(startId:Int){
        mDengMiVM.qryNextPage(startId).observe(this, Observer {
            mDataList = if(startId==0){
                mRefreshLayout.isRefreshing = false
                it!!
            } else {
                mDataList.plus(it!!)
            }
            mAdapter.notifyDataSetChanged(mDataList)
            val dataEmpty= it.isEmpty()
            val hasMore= it.size >= 10
            mRecyclerView.loadMoreFinish(dataEmpty, hasMore)
        })
    }

//
//
//    /**
//     * 第一次加载数据。
//     */
//    private fun loadData() {
////        mDataList = createDataList(0)
//        mDengMiVM.qryNextPage(0).observe(this, Observer {
//            mDataList=it!!
//            mAdapter.notifyDataSetChanged(mDataList)
//
////            mRefreshLayout.setRefreshing(false)
//
//            // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
//            // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
//            // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
//            val dataEmpty=if(mDataList.isEmpty())true else false
//            mRecyclerView.loadMoreFinish(dataEmpty, true)
//        })
//    }

//    protected fun createDataList(start: Int): ArrayList<String> {
//        val strings = ArrayList<String>()
//        for (i in start until start + 100) {
//            strings.add("第" + i + "个Item")
//        }
//        return strings
//    }
}
