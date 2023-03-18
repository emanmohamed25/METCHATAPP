package com.example.chatapp.doctor.newchat.createnewchate.data

class DepartmentRequest(_department_id:String,_level_id:String,_section_id: ArrayList<String>,_message:String) {
    var department_id:String=null!!
    get() = field
    set(_department_id) {
        field = _department_id
    }
    var level_id:String?=null
        get() = field
        set(_level_id) {
            field = _level_id
        }
    var section_id:MutableList<String> =_section_id
        get() = field
        set(_section_id) {
            field = _section_id
        }
    var message:String=null!!
    get() = field
    set(_message) {
        field = _message
    }
}