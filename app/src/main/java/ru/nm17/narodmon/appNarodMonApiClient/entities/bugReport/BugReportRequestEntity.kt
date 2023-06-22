package ru.nm17.narodmon.appNarodMonApiClient.entities.bugReport

data class BugReportRequestEntity(
    val api_key: String,
    val cmd: String,
    val email: String,
    val logs: String,
    val mess: String,
    val name: String,
    val time: Int,
    val uuid: String
)