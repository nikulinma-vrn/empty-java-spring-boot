
GET("http://localhost:8080/rest/admin-ui/customers") {

}
request("METHOD", "http://localhost:8080/path") {
    header("Content-Type", "application/json")
    body(
            """
            {}
            """.trimIndent()
    )
}