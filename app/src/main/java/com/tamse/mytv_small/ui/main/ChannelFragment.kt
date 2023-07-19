package com.tamse.mytv_small.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tamse.mytv_small.R
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChannelFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: ChannelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.photosGrid.adapter = ChannelAdapter(ChannelListener { channel ->
            viewModel.fetchChannelClicked(channel)
//            viewModel.fetchPlayUrl()
            findNavController()
                .navigate(R.id.action_channelFragment2_to_channelDetailFragment)
        })

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchChannels()
    }
}