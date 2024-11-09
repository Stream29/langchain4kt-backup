package io.github.stream29.langchain4kt.api.baiduqianfan

public data class QianFanGenerationException(
    val info: RequestError
) : Exception(info.errorMsg)

public data class QianFanTokenFetchException(
    val info: AccessTokenError
) : Exception(info.errorDescription)