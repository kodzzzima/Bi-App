package com.example.biapp.presentation.message

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.biapp.R
import com.example.biapp.databinding.FragmentSingleChatBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class SingleChatFragment : Fragment(R.layout.fragment_single_chat) {
    private lateinit var binding: FragmentSingleChatBinding

    private val viewModel: SingleChatViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var title = ""
    val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleChatBinding.bind(view)
        (activity as AppCompatActivity).supportActionBar?.title = title
        val userLogin = sharedPreferences.getString("user_id", "") ?: ""


        viewModel.liveData.observe(viewLifecycleOwner) {
            val userChat1Title = sharedPreferences.getString("${it}_chat1title", "") ?: ""
            val userChat1Message = sharedPreferences.getString("${it}_chat1message", "") ?: ""
            if (userChat1Title.isEmpty()) {
            } else {
                binding.blockUserMessage.visibility = View.VISIBLE
                binding.chatUserMessage.text = userChat1Message
                binding.chatUserMessageTime.text = currentTime
            }
        }
        binding.chatBtnImageMessage.setOnClickListener {
            binding.blockUserMessage.visibility = View.VISIBLE
            binding.chatUserMessage.text = binding.chatInputMessage.text
            binding.chatUserMessageTime.text = currentTime
            binding.chatInputMessage.text = null

            sharedPreferences.edit()
                .putString("${userLogin}_chat1title", title)
                .apply()

            sharedPreferences.edit()
                .putString("${userLogin}_chat1message", binding.chatUserMessage.text.toString())
                .apply()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        title = arguments?.getString("chatTitle").toString()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}