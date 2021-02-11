package gallery.app.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import gallery.app.common.utils.viewBinding

abstract class BaseActivity<DB:ViewDataBinding,VM:ViewModel>(val resLayoutID:Int) : AppCompatActivity(resLayoutID){

    protected lateinit var mBinding: DB
    protected abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,resLayoutID)
        mBinding.lifecycleOwner = this
    }
}
