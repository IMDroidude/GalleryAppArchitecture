package gallery.app.architecture.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.databinding.ItemPhotoBinding
/*
import gallery.app.common.GenericAdapter

class TestAdapter(items:List<Models.PhotoResponse>) : GenericAdapter<Models.PhotoResponse>(items) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out ViewBinding, Models.PhotoResponse> =
        ListMovieViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    private inner class ListMovieViewHolder(
        binding: ItemPhotoBinding
    ) : BaseViewHolder<ItemPhotoBinding, Models.PhotoResponse>(binding) {
        override fun bindTo(item: Models.PhotoResponse?) {

        }
    }
}*/
