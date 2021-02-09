package gallery.app.architecture.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
    }

    private fun bindBundle() {
        PhotoDetailFragmentArgs.fromBundle(requireArguments()).apply {
            val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
            mBinding.imgLargePhoto.setImageURI(imgUri)
            Glide.with(requireContext())
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(mBinding.imgLargePhoto)
            mBinding.txtTags.text = tags
            mBinding.txtUserName.text = userName

        }
    }
}