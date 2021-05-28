package com.bluetoastquest.quest

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.bluetoastquest.*
import com.bluetoastquest.base.BaseFragment
import com.bluetoastquest.utils.*
import kotlinx.android.synthetic.main.fragment_quest.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_quest
    override val viewModel: QuestViewModel by viewModel()

    private val args: QuestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate(args.questId)
    }

    override fun bindViewModel() = with(viewModel) {
        bottomButton.onSingleClick { onBottomButtonClicked() }

        bind(navigateToScanner) { navigateToScanner() }
        bind(navigateBack) { navigateBack() }
        bind(scanError) { showScannerError() }
        bindText(mainText, textViewDescription)
        bindText(screenTitle, textViewTitle)
        bindText(bottomButtonText, bottomButton)
        bindDrawable(imageId, imaveViewStepImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
        viewModel.onResult(data.toScanResult(requestCode, resultCode))
}