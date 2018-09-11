package com.bankex.pay.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.*
import com.bankex.pay.R
import com.bankex.pay.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.ac_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainActivity : BaseActivity(), MainView {

    private var sectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun getLayout(): Int = R.layout.ac_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val get = intent.extras.get(ARG_ADDRESS_NUMBER)

        setSupportActionBar(toolbar)
        sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = sectionsPagerAdapter
        initBottomBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initBottomBar() {
        footerView.setOnTabSelectListener({ tabId ->
            when (tabId) {
                R.id.home -> switchFragment()
                R.id.wallet -> switchFragment()
                R.id.contacts -> switchFragment()
                R.id.history -> switchFragment()
            }
        }, false)
    }

    private fun switchFragment() {

    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 3
        }
    }


    companion object {
        public val ARG_ADDRESS_NUMBER = "address_number"
        fun makeIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
