package com.huawei.hayriaral.sitekitdemo.util

interface RequestResult<any : Any> {
    fun onSuccess(any: Any)
    fun onFail()
}