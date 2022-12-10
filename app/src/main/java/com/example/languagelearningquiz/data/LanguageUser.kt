package com.example.languagelearningquiz.data

data class LanguageUser(
    var displayName: String = "",
    var language: String = "",
    var ChScore: Int = 0,
    var SpScore: Int = 0,
    var uid: String = ""
)