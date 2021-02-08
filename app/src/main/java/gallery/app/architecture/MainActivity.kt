package gallery.app.architecture

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gallery.app.architecture.databinding.ActivityMainBinding
import gallery.app.architecture.main.MainViewModel
import gallery.app.common.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main) {
    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.lifecycleOwner = this
        ///setContentView(R.layout.activity_main)
        mBinding.testText.text = "hilt integrated successfully"



    }
}