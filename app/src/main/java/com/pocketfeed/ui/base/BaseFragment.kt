package com.pocketfeed.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.pocketfeed.ui.callback.OnBackPressed
import com.pocketfeed.ui.viewmodel.ViewModelProvidersFactory
import com.pocketfeed.utils.CustomProgressBar
import com.pocketfeed.utils.hideKeyboard
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment(),
    OnBackPressed {

    lateinit var binding: VDB
    private lateinit var viewModel: VM
    private lateinit var rootView: View
    private val progressBar: CustomProgressBar = CustomProgressBar()

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initViewModel()
        rootView = binding.root
        return rootView
    }


    @LayoutRes
    protected abstract fun getLayoutId(): Int


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory)
            .get(setViewModel())
    }

    fun getViewModel(): VM {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        progressBar.setProgressDialog(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressBar.hideProgress()
    }


    /**
     * Used to retrieve the concrete version of the
     * initialized [BaseViewModel].
     *
     * @return the initialized [BaseViewModel]
     */
    protected abstract fun setViewModel(): Class<VM>


    override fun onBackPressed() {
        hideKeyboard()
    }

    /**
     * Hides the system software keyboard.
     *
     * @param clearFocus whether the focus should be cleared from the target view or not
     */
    private fun hideKeyboard(clearFocus: Boolean = true) {
        hideKeyboard(rootView, clearFocus)
    }


    /**
     * Hides the system software keyboard.
     *
     * @param targetView the view that's requesting the keyboard to be hidden
     * @param clearFocus whether the focus should be cleared from the target view or not
     */
    fun hideKeyboard(targetView: View, clearFocus: Boolean = true) {
        targetView.hideKeyboard(clearFocus)
    }


    fun showProgressDialog() {
        progressBar.showProgress()
    }

    fun dismissProgressDialog() {
        progressBar.hideProgress()
    }

}