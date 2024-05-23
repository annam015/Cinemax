package com.example.cinemax.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cinemax.R
import com.example.cinemax.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.cardNews.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
        }

        binding.cardDiscoverMovies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_discoverMoviesFragment)
        }

        binding.cardMaps.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
