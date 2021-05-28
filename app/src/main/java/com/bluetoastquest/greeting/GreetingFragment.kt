package com.bluetoastquest.greeting

import com.bluetoastquest.R
import com.bluetoastquest.base.BaseFragment
import com.bluetoastquest.utils.bind
import com.bluetoastquest.utils.navigateBack
import com.bluetoastquest.utils.onSingleClick
import kotlinx.android.synthetic.main.fragment_greeting.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GreetingFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_greeting
    override val viewModel: GreetingViewModel by viewModel()

    override fun bindViewModel() = with(viewModel) {
        bind(navigateBack) { navigateBack() }
        buttonStart.onSingleClick { onButtonStartClicked() }
    }
}
