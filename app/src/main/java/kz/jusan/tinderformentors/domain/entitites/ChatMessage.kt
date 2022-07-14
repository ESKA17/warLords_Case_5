package kz.jusan.tinderformentors.domain.entitites

import androidx.annotation.Keep
import com.google.firebase.database.ServerValue
import kz.jusan.tinderformentors.presentation.models.ChatReceiver
import kz.jusan.tinderformentors.presentation.models.ChatSender

@Keep
data class ChatMessage(
    val timestamp: Long? = 0,
    var author: String? = "",
    var sentTo: String? = "",
    var message: String? = ""
)

fun ChatMessage.toChatSender(): ChatSender {
    return ChatSender(
        dateSent = mapOf("timestamp" to ServerValue.TIMESTAMP),
        author = author ?: "",
        sentTo = author ?: "",
        message = message ?: ""
    )
}

fun ChatMessage.toChatReceiver(): ChatReceiver {
    return ChatReceiver(
        dateSent = mapOf("timestamp" to ServerValue.TIMESTAMP),
        author = author ?: "",
        sentTo = sentTo ?: "",
        message = message ?: ""
    )
}