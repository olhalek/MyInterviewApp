package com.example.myinterviewapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myinterviewapp.R
import com.example.myinterviewapp.adapter.GifsAdapter
import com.example.myinterviewapp.databinding.FragmentFirstBinding
import com.example.myinterviewapp.viewModel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private lateinit var gifsAdapter: GifsAdapter

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.getGifs()

        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun initObservers() {
        viewModel.gifsList.observe(viewLifecycleOwner) { list ->
            lifecycleScope.launch {
                gifsAdapter.setGifs(list)

                gifsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        gifsAdapter = GifsAdapter(requireContext())

        binding.rvList.apply {
            this.adapter = gifsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, GRID_SPAN_SIZE)
        }

        gifsAdapter.setOnCLickListener(object : GifsAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                bundle.putString(URL_TAG, viewModel.getGifUrl(position))

                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }
    
    companion object {
        
        const val URL_TAG = "url"

        private const val GRID_SPAN_SIZE = 2
    }
}