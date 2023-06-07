package com.example.chatapp.student.pusher

object PusherEventBus {
    private val listeners = mutableListOf<PusherEventListener>()

    fun registerListener(listener: PusherEventListener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: PusherEventListener) {
        listeners.remove(listener)
    }

    fun postPusherEvent(eventName: String, eventData: String) {
        for (listener in listeners) {
            listener.onPusherEventReceived(eventName, eventData)
        }
    }
}