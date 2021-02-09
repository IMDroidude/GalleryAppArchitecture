package gallery.app.architecture.ui.photos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gallery.app.architecture.R
import gallery.app.architecture.databinding.FragmentPhotoDetailBinding
import gallery.app.common.BaseFragment

@AndroidEntryPoint
class PhotoDetailFragment : BaseFragment<FragmentPhotoDetailBinding,PhotoDetailFragmentViewModel>
    (R.layout.fragment_photo_detail) {

    override val mViewModel: PhotoDetailFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}