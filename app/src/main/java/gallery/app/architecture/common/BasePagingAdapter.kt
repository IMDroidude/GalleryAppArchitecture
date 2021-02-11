package gallery.app.architecture.common

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import de.joyn.myapplication.network.dto.Models

abstract class BasePagingAdapter<T:Models>(itemCallback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseViewHolder<out ViewBinding, T>>(itemCallback) {
    override fun onBindViewHolder(holder: BaseViewHolder<out ViewBinding, T>, position: Int) =
        holder.bindTo(getItem(position))
}