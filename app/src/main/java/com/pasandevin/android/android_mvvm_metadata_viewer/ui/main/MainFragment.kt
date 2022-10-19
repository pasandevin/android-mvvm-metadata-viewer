package com.pasandevin.android.android_mvvm_metadata_viewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pasandevin.android.android_mvvm_metadata_viewer.Adapter.VideoAdapter
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.asDatabaseModelFromDev
import com.pasandevin.android.android_mvvm_metadata_viewer.VideosDatabase
import com.pasandevin.android.android_mvvm_metadata_viewer.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var db: VideosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.vm = viewModel

        viewModel.playlist.observe(viewLifecycleOwner) {

        //insert content to listview
        binding.recyclerview.layoutManager = LinearLayoutManager(view?.context)
        binding.recyclerview.adapter = VideoAdapter(it)

        //insert to db
        db = VideosDatabase.getDatabase(requireContext())
        viewModel.insertToDB(db)

        }
        return binding.root
    }

}