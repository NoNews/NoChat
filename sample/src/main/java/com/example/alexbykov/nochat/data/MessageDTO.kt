package com.example.alexbykov.nochat.data

import ru.alexbykov.nochat.markers.NoChatMessage
import java.util.*

/**
 *  @author Alex Bykov
 *  Date: 23.10.17.
 *
 *  You can contact me at me@alexbykov.ru
 */
class MessageDTO : NoChatMessage {

    var messageId: String? = null
    var chat: String? = null
    var chatType: String? = null
    //    var content: ModelContent? = null
    var created: Date? = null
    var from: String? = null
    var important: Boolean? = null
    var isFirst: Boolean? = null
    var isGroup: Boolean? = null
    var isLast: Boolean? = null
    var text: String? = null
    //    var linkedMessages: RealmList<ModelMessage>? = null
//    var links: RealmList<ModelLinks>? = null
    var read: Boolean? = null
    var to: String? = null
    var image: String? = null
    var authorPhoto: String? = null
    var linkedMessages: List<MessageDTO>? = null
    var isBubbleWithAngle = true
    var selected = false

}