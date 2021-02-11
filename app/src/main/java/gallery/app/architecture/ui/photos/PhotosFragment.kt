package gallery.app.architecture.ui.photos

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding,PhotosFragmentViewModel>(R.layout.fragment_photos),
    SearchView.OnQueryTextListener {
    override val mViewModel: PhotosFragmentViewModel by viewModels()
    ///private val binding by viewBinding(FragmentPhotosBinding::bind)

    private lateinit var adapter: PhotoCollectionAdapter
    /*private val clickListener: ClickListener = this::onPhotoClicked
    private fun onPhotoClicked(photo: Models.PhotoResponse) {
        view?.let {
            Navigation.findNavController(it).navigate(
                PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(
                    photo.previewImageUrl,
                    photo.userName,
                    photo.tags
                )
            )
        }
    }

    private val photoListAdapter = PhotoAdapter(clickListener)
*/
    override fun onStart() {
        super.onStart()
        startObserving()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        //initRecyclerView()
        mViewModel.setFilter(getString(R.string.search_filter_default_value))

        initAdapter()
        /*mViewModel.popularPhotos.observe(viewLifecycleOwner,{
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        })*/
        lifecycleScope.launch {
            /*mViewModel.fetchPhotoCollection().collect {
                adapter.submitData(it)
            }*/
        }
    }

    private fun initAdapter() {
        adapter = PhotoCollectionAdapter()
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerView.adapter = adapter/*.withLoadStateHeaderAndFooter(
            ItemLoadStateAdapter(adapter::retry),
            ItemLoadStateAdapter(adapter::retry)
        )*/
        mBinding.recyclerView.setHasFixedSize(true)

        adapter.addLoadStateListener { loadState ->
            mBinding.recyclerView.isVisible = loadState.refresh is LoadState.NotLoading
            ///binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
            ///binding.btnRetry.isVisible = loadState.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                /*toast(requireContext(), it.error.toString())
                it.error.log()*/
            }
        }
    }
    protected fun startObserving() {
        /*mViewModel.stateLiveData.observe(requireActivity(),{
            render(it)
        })*/
    }

    /*private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
    }*/

    /*private fun render(pagedPhotoList: PagedList<Models.PhotoResponse>) {
        photoListAdapter.submitList(pagedPhotoList)
        Timber.d("pagedPhotoList : %s", pagedPhotoList)
    }*/

    override fun onQueryTextSubmit(p0: String?): Boolean = false
    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("query : %s", newText)
        if (newText!!.trim().replace(" ", "").length >= 3 || newText!!.isEmpty()) {
            mViewModel.cachedFilter = newText
            mViewModel.setFilter(newText!!)
            ///mViewModel.createLiveData()
            startObserving()

        }
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