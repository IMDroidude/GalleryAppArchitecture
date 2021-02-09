package gallery.app.architecture.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.R

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(photo: Models.PhotoResponse?) {
        if (photo != null) {
            with(photo) {
                var imgUrl = previewImageUrl
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                val imgPreview = itemView.findViewById<ImageView>(R.id.imgPreview)
                val txtLikes = itemView.findViewById<TextView>(R.id.txtLikes)
                val txtDownload = itemView.findViewById<TextView>(R.id.txtDownload)
                val txtUserName = itemView.findViewById<TextView>(R.id.txtUserName)
                val txtView = itemView.findViewById<TextView>(R.id.txtView)
                itemView.apply {
                    Glide.with(imgPreview.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                        )
                        .into(imgPreview)
                    txtLikes.text = likeNumber
                    txtDownload.text = downloadNumber
                    txtUserName.text = userName
                    txtView.text = viewNumber
                }
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_photo, parent, false)

            return PhotoViewHolder(view)
        }
    }

}