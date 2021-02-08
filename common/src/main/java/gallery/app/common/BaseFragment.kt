package gallery.app.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<DB: ViewDataBinding,VM: ViewModel>(val resLayoutID:Int) : Fragment(resLayoutID) {

    protected abstract val mViewModel: VM
    protected lateinit var mBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, resLayoutID, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }
}