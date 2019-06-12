package com.cope.copelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cope_list.*


class CopeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cope_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context ?: return
        rvCopes?.layoutManager = LinearLayoutManager(context)
        rvCopes?.setHasFixedSize(true)
        rvCopes?.adapter = CopeAdapter()
    }

    companion object{
        fun newInstance(): CopeListFragment {
            return CopeListFragment()
        }
    }
}
