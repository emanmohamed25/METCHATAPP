package com.example.chatapp.student.pusher

interface PusherEventListener {
    fun onPusherEventReceived(eventName: String, eventData: String)
}
