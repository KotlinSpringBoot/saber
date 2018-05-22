package com.light.saber.controller

data class Result<T>(var data: T, var msg: String, var success: Boolean)