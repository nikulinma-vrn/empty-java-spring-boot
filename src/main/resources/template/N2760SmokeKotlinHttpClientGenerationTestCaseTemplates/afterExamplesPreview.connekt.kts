val host: String by env
val showEnv: String by env

// GET request with a header
GET("https://examples.http-client.intellij.net/ip") {
    headers("Accept" to "application/json")
}

// GET request with parameter
GET("https://examples.http-client.intellij.net/get?show_env=1") {
    headers("Accept" to "application/json")
}

// GET request with parameter in DSL
GET("https://examples.http-client.intellij.net/get") {
    headers("Accept" to "application/json")
    queryParam("show_env", 1)
}

// GET request with environment variables
GET("$host/get?show_env=$showEnv") {
    headers("Accept" to "application/json")
}

// GET request without cookies
GET("https://examples.http-client.intellij.net/cookies") {
    noCookies()
}

// GET request with disabled redirects
GET("https://examples.http-client.intellij.net/status/301") {
    noRedirect()
}