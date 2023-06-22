package ru.nm17.narodmon.appNarodMonApiClient.entities.sendComplaint

data class SendComplaintRequestEntity(
    val api_key: String,
    val cmd: String,
    val email: String,
    val id: Int,
    val name: String,
    val problem: String,
    val time: Int,
    val uuid: String,
    val value: Int
)