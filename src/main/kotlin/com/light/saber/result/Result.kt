package com.light.saber.result

data class Result<T>(var data: T, var msg: String, var success: Boolean)