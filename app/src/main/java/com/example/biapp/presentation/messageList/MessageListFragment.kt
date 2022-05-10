package com.example.biapp.presentation.messageList

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.biapp.R
import com.example.biapp.databinding.FragmentMessageListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MessageListFragment : Fragment(R.layout.fragment_message_list) {

    private val viewModel: MessageListViewModel by viewModels()

    private lateinit var binding: FragmentMessageListBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMessageListBinding.bind(view)

        viewModel.liveData.observe(viewLifecycleOwner) {
            val userChat1Title = sharedPreferences.getString("${it}_chat1title", "") ?: ""
            val userChat1Message = sharedPreferences.getString("${it}_chat1message", "") ?: ""
            if (userChat1Title.isEmpty()) {
                binding.listIsEmpty.visibility = View.VISIBLE
                binding.card.visibility = View.GONE
            } else {
                binding.company.text = userChat1Title
                binding.message.text = "Вы: " + userChat1Message
            }
            binding.card.setOnClickListener {
                val bundle = bundleOf("chatTitle" to userChat1Title)
                findNavController().navigate(R.id.action_messageListFragment_to_singleChatFragment,
                    bundle)
            }
        }


    }

}