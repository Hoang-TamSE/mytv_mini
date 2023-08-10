package com.tamse.mytv_small.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tamse.mytv_small.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChannelFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: ChannelViewModel by activityViewModels()

    private var channelClickListener: ChannelClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChannelClickListener) {
            channelClickListener = context
        } else {
            throw RuntimeException("$context must implement ChannelClickListener")
        }
    }


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
//            findNavController()
//                .navigate(R.id.action_channelFragment2_to_channelDetailFragment)
//            startActivity(Intent(context, ExoPlayerActivity::class.java))
            channelClickListener?.onChannelClicked()

        })

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchChannels()
    }

    override fun onDetach() {
        super.onDetach()
        channelClickListener = null
    }

    interface ChannelClickListener {
        fun onChannelClicked()
    }
}

