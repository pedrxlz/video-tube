package com.example.youtubeapi.ui.trendings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.adapter.VideoAdapter
import com.example.youtubeapi.databinding.FragmentVideoBinding

class TrendingsFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private var videoViewModel: TrendingsViewModel? = null
    private val adapter = VideoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvVideo.adapter = adapter
        binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())

        videoViewModel?.video?.observe(viewLifecycleOwner) {
            if (it != null && it.items.isNotEmpty()) {
                adapter.setData(it.items)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        videoViewModel =
            ViewModelProvider(this).get(TrendingsViewModel::class.java)


        _binding = FragmentVideoBinding.inflate(inflater, container, false)

        return binding.root
    }


}