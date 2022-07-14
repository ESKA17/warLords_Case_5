package kz.jusan.tinderformentors.presentation.models

data class ChatReceiver(
    val dateSent: Map<String, MutableMap<String, String>>,
    var author: String,
    var sentTo: String,
    var message: String
) : ChatItem
