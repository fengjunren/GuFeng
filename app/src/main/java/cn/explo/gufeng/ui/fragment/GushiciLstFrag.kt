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
import cn.explo.gufeng.databinding.GushiciLstFragBinding
import cn.explo.gufeng.entity.GuShiCi
import cn.explo.gufeng.vm.GushiciVM
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView


/**
 * A simple [Fragment] subclass.
 *
 */
class GushiciLstFrag : BaseNestFragment() {
    private lateinit var  dBinding : GushiciLstFragBinding

    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView: SwipeMenuRecyclerView
    private lateinit var mAdapter: MultiTypeAdapter
    private  lateinit var mDataList:  List<GuShiCi>
    private lateinit var mGscVM: GushiciVM

    override fun getLayoutId(): Int {
        return R.layout.gushici_lst_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as GushiciLstFragBinding
        title="古诗词"
        mGscVM=getViewModel()
        mDataList= arrayListOf()
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        mRefreshLayout = dBinding.refreshLayout
        mRefreshLayout.setOnRefreshListener(mRefreshListener) // 刷新监听。

        mRecyclerView = dBinding.recyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.setSwipeItemClickListener(mItemClickListener) // RecyclerView Item点击监听。

        mRecyclerView.useDefaultLoadMore() // 使用默认的加载更多的View。
        mRecyclerView.setLoadMoreListener(mLoadMoreListener) // 加载更多的监听。

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
    }

    /**
     * Item点击监听。
     */
    private val mItemClickListener = SwipeItemClickListener { itemView, position ->
        val parent=parentFragment as NestContainFragment
        val args= Bundle().apply {
            putInt("position",position)
        }
        val destFrag=GushiciContainFrag().apply {
            arguments=args
        }

        parent.showNextFragment(this@GushiciLstFrag,destFrag)
    }

    private fun loadData(startId:Int){
        mGscVM.qryNextPage(startId).observe(this, Observer {
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


}
