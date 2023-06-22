package ru.nm17.narodmon.appNarodMonApiClient.entities.sendMessage

data class SendMessageRequestEntity(
    val api_key: String,
    val cmd: String,
    val email: String,
    val mess: String,
    val name: String,
    val subj: String,
    val uid: Int,
    val uuid: String
)