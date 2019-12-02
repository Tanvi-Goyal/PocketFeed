package com.pocketfeed.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.pocketfeed.ui.viewmodel.ViewModelProvidersFactory
import com.pocketfeed.utils.CustomProgressBar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewDataBinding, ViewModel : BaseViewModel> :
    DaggerAppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun loadState(bundle: Bundle)
    abstract fun saveState(bundle: Bundle)

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    private val progressBar: CustomProgressBar = CustomProgressBar()

    lateinit var binding: Binding
    private lateinit var viewModel: ViewModel

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        progressBar.setProgressDialog(this)
        when {
            savedInstanceState != null -> loadState(savedInstanceState)
            intent.extras != null -> loadState(intent.extras!!)
            else -> loadState(Bundle.EMPTY)
        }
        binding = DataBindingUtil.setContentView(this, getLayoutId()) as Binding
    }


    final override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveState(outState)
    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory)
            .get(setViewModel())
    }


    fun getViewModel(): ViewModel {
        return viewModel
    }

    /**
     * Used to retrieve the concrete version of the
     * initialized [BaseViewModel].
     *
     * @return the initialized [BaseViewModel]
     */
    protected abstract fun setViewModel(): Class<ViewModel>

    override fun onDestroy() {
        super.onDestroy()
        progressBar.hideProgress()
    }

    fun showProgressDialog() {
        progressBar.showProgress()
    }

    fun dismissProgressDialog() {
        progressBar.hideProgress()
    }
}