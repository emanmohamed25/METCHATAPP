package com.example.chatapp.doctor.newchat.admin.createnewchate.data

import retrofit2.http.Multipart

data class DepartmentRequest(
    var department_id: String,
    var level_id: String?,
    var section_id: MutableList<String>?,
    var message: String
)
//{
//    var department_id:String=_department_id
//    get() = field
//    set(_department_id) {
//        field = _department_id
//    }
//    var level_id:String?=_level_id
//        get() = field
//        set(_level_id) {
//            field = _level_id
//        }
////    var section_id:MutableList<String>? =_section_id
////        get() = field
////        set(_section_id) {
////            field = _section_id
////        }
//    var message:String=_message
//    get() = field
//    set(_message) {
//        field = _message
//    }
//}