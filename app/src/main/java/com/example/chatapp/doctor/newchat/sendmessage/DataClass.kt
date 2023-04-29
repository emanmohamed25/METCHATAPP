package com.example.chatapp.doctor.newchat.sendmessage

class DataClass(_content: String, _students: ArrayList<Int>) {
    var status: String? = null
        get() = field
        set(_status) {
            field = _status
        }
    var message: String? = null
        set(_message) {
            //  message = _message
            field = _message
        }
        get() = field
    var content: String? =_content

    var students :MutableList<Int> =_students
}