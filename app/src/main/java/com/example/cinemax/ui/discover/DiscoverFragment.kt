package com.example.cinemax.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinemax.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private lateinit var discoverViewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        discoverViewModel = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupSpinners()

        binding.discoverButton.setOnClickListener {
            discoverViewModel.releaseYear = binding.spinnerReleaseYear.selectedItem as String?
            discoverViewModel.genre = binding.spinnerGenre.selectedItem as String?
            discoverViewModel.sortBy = binding.spinnerSortBy.selectedItem as String?
            discoverViewModel.discoverMovies()
            val intent = Intent(activity, MoviesActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    private fun setupSpinners() {
        val releaseYearsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            discoverViewModel.releaseYears
        )
        releaseYearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerReleaseYear.adapter = releaseYearsAdapter

        val genresAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            discoverViewModel.genreOptionsKeys
        )
        genresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGenre.adapter = genresAdapter

        val sortByAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            discoverViewModel.sortOptions
        )
        sortByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSortBy.adapter = sortByAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}