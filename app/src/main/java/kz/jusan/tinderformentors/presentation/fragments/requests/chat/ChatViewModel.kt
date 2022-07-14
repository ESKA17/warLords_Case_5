package kz.jusan.tinderformentors.presentation.fragments.requests.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kz.jusan.tinderformentors.domain.entitites.ChatMessage
import kz.jusan.tinderformentors.domain.entitites.toChatReceiver
import kz.jusan.tinderformentors.domain.entitites.toChatSender
import kz.jusan.tinderformentors.presentation.models.ChatItem
import java.util.*

class ChatViewModel : ViewModel() {

    private val _chatMessages = MutableLiveData<List<ChatItem>>().apply {
        value = emptyList()// INITIAL_MESSAGES_LIST.reversed()
    }
    val chatMessages: LiveData<List<ChatItem>> = _chatMessages

    private fun prependChatItem(chatItem: ChatItem) {
        val currentList = _chatMessages.value?.toMutableList()
        currentList?.add(0, chatItem)
        _chatMessages.value = currentList
    }

    fun prependChatItemFirebase(chatItem: ChatItem) {
        prependChatItem(chatItem)
        val database = Firebase.database
        val myRef = database.reference
        myRef.child("messages").child(UUID.randomUUID().toString())
            .setValue(chatItem)
    }

    fun readListener(author: String, sentTo: String) {
        val currentList = _chatMessages.value?.toMutableList()

        val database = Firebase.database
        val myRef = database.reference

        myRef
            .child("messages")
            .orderByChild("dateSent/timestamp")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    currentList?.clear()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue<ChatMessage>()?.let {
                            if (it.author == author)
                                currentList?.add(0, it.toChatSender())
                            else
                                currentList?.add(0, it.toChatReceiver())
                        }
                    }
                    _chatMessages.value = currentList
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("ChatViewModel", error.toString())
                }
            })

    }

}