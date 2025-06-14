package com.udara.simplecrud

data class Job(
    val title: String,
    val type: String,
    val salary: String,
    val location: String,
) {
    constructor() : this("", "", "", "")
}
