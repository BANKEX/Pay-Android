package com.bankex.wallet.ui.onboarding

import android.os.Bundle
import android.view.View
import com.bankex.wallet.R
import com.bankex.wallet.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.fr_content_onboarding.*

private const val ARG_ID = "param_id"
private const val ARG_TITLE = "param_title"
private const val ARG_DESC = "param_desc"

class ContentOnBoardingFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fr_content_onboarding

    private var paramID: Int? = null
    private var paramTitle: Int? = null
    private var paramDesc: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramID = it.getInt(ARG_ID)
            paramTitle = it.getInt(ARG_TITLE)
            paramDesc = it.getInt(ARG_DESC)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageOnboarding.setImageResource(this!!.paramID!!)
        titleOnboarding.setText(this!!.paramTitle!!)
        descOnboarding.setText(this!!.paramDesc!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Int, param3: Int) =
                ContentOnBoardingFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, param1)
                        putInt(ARG_TITLE, param2)
                        putInt(ARG_DESC, param3)
                    }
                }
    }
}
