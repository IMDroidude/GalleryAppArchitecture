package gallery.app.architecture.test

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gallery.app.architecture.R
import gallery.app.architecture.databinding.ActivityTestBinding
import gallery.app.architecture.ui.photos.PhotosFragment
import gallery.app.common.BaseActivity

@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding,TestViewModel>(R.layout.activity_test) {
    override val mViewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.frame_container,PhotosFragment(),
            "PhotosFragment").commit()
    }
}