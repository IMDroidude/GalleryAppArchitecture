package gallery.app.architecture.ui.photos

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.LoadState
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.R
import gallery.app.architecture.databinding.FragmentPhotosBinding
import gallery.app.common.BaseFragment
import gallery.app.common.utils.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding,PhotosFragmentViewModel>(R.layout.fragment_photos),
    SearchView.OnQueryTextListener, LifecycleObserver {
    override val mViewModel: PhotosFragmentViewModel by viewModels()

    private lateinit var photoAdapter: PhotoCollectionAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        ///mViewModel.setFilter(getString(R.string.search_filter_default_value))
        initAdapter()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated(){
        mViewModel.trendingPhotos.observe(viewLifecycleOwner, Observer {
            photoAdapter.submitData(lifecycle,it)
        })
    }

    private fun initAdapter() {
        photoAdapter = PhotoCollectionAdapter()
        photoAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = photoAdapter
        }

        photoAdapter.addLoadStateListener { loadState ->
            mBinding.recyclerView.isVisible = loadState.refresh is LoadState.NotLoading

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
            }
        }
    }

    var timer: CountDownTimer? = null
    override fun onQueryTextSubmit(p0: String?): Boolean = false
    override fun onQueryTextChange(newText: String?): Boolean {

        timer?.cancel()
        timer = object : CountDownTimer(1000, 2500) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                Timber.d("query : %s", newText)
                if (newText!!.trim().replace(" ", "").length >= 3) {
                    mViewModel.cachedFilter = newText
                    mViewModel.setFilter(newText)
                }
                ///afterTextChanged.invoke(editable.toString())
            }
        }.start()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        //val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
            queryHint = getString(R.string.search_view_hint)
            setQuery(
                if (mViewModel.cachedFilter.isEmpty()) getString(R.string.search_filter_default_value) else mViewModel.cachedFilter,
                true
            )
            isSubmitButtonEnabled = true
        }.setOnQueryTextListener(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return view?.let {
            NavigationUI.onNavDestinationSelected(item,it.findNavController())
        }?: kotlin.run {
            super.onOptionsItemSelected(item)
        }
    }
}