package com.devss.todoappkt

// A data class in kotlin is just a class whose primary purpose is to hold data
// no logic
// only single constructor with properties
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)