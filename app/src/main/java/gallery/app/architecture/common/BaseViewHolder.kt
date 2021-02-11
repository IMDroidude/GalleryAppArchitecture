package gallery.app.architecture.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VB : ViewBinding, T>(internal val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindTo(item: T?)

    fun setOnClickListener(listener: View.OnClickListener?) = binding.root.setOnClickListener(listener)
    fun setOnLongClickListener(listener: View.OnLongClickListener?) = binding.root.setOnLongClickListener(listener)
}