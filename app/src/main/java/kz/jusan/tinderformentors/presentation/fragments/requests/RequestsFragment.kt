package kz.jusan.tinderformentors.presentation.fragments.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kz.jusan.tinderformentors.databinding.FragmentRequestsBinding
import kz.jusan.tinderformentors.presentation.MainViewModel
import kz.jusan.tinderformentors.presentation.adapters.RequestsAdapter

class RequestsFragment : Fragment() {

    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val requestsAdapter = RequestsAdapter()
        binding.rvMatches.apply {
            adapter = requestsAdapter
            layoutManager = LinearLayoutManager(root.context)
        }

        mainViewModel.matches.observe(viewLifecycleOwner) {
            requestsAdapter.matches = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}