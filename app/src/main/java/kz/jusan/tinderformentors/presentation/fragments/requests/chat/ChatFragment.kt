package kz.jusan.tinderformentors.presentation.fragments.requests.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ServerValue
import kz.jusan.tinderformentors.databinding.FragmentChatBinding
import kz.jusan.tinderformentors.presentation.adapters.ChatAdapter
import kz.jusan.tinderformentors.presentation.models.ChatSender


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(layoutInflater)
        val root: View = binding.root

        val loggedInUserEmail = getSavedEmail()
        val receiverUserEmail = arguments?.getString("email") ?: "NULL"
        val receiverUserFullName = arguments?.getString("fullName") ?: "NULL"

        binding.tabChatTitle.title = receiverUserFullName

        val chatAdapter = ChatAdapter()
        binding.rvChat.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
            itemAnimator = DefaultItemAnimator()
        }

        chatViewModel.chatMessages.observeForever { chatMessages ->
            chatAdapter.setItems(chatMessages)
        }

        binding.ibSendMessage.setOnClickListener {
            val timestampMap = mapOf(
                "timestamp" to ServerValue.TIMESTAMP
            )

            val enteredMessage = binding.etMessage.text.toString()
            val chatMessage = ChatSender(
                dateSent = timestampMap,
                author = loggedInUserEmail,
                sentTo = receiverUserEmail,
                message = enteredMessage
            )

            if (enteredMessage.isNotBlank()) {
                chatViewModel.prependChatItemFirebase(chatMessage)
                binding.etMessage.text.clear()
            }
        }

        chatViewModel.readListener(loggedInUserEmail, receiverUserEmail)

        binding.ibReport.setOnClickListener {
            ReportDialog().show(
                childFragmentManager,
                null
            )
        }

        return root
    }

    private fun getSavedEmail(): String {
        val prefs: SharedPreferences = requireActivity().getSharedPreferences(
            "MyPreference",
            AppCompatActivity.MODE_PRIVATE
        )
        return prefs.getString("email", "ERROR") ?: "ERROR"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}