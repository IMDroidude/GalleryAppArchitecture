package gallery.app.architecture.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.R
import gallery.app.architecture.common.BasePagingAdapter
import gallery.app.architecture.common.BaseViewHolder
import gallery.app.architecture.databinding.ItemPhotoBinding

class PhotoCollectionAdapter : BasePagingAdapter<Models.PhotoResponse>(PhotoCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewBinding, Models.PhotoResponse> =
        PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    private inner class PhotoViewHolder(
        binding: ItemPhotoBinding
    ) : BaseViewHolder<ItemPhotoBinding, Models.PhotoResponse>(binding) {
        override fun bindTo(item: Models.PhotoResponse?) = with(binding) {
            // FIXME: 2/10/21 instead bind item in xml
            if (item != null) {
                val imgUrl = item.previewImageUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imgPreview.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imgPreview)
                txtLikes.text = item.likeNumber
                txtDownload.text = item.downloadNumber
                txtUserName.text = item.userName
                txtView.text = item.viewNumber
            }
        }
    }
    companion object {
        private object PhotoCallback : DiffUtil.ItemCallback<Models.PhotoResponse>() {
            override fun areItemsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Models.PhotoResponse, newItem: Models.PhotoResponse): Boolean = oldItem == newItem
        }
    }
}