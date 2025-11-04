package com.example.financeapp.core

object Config {
    var apiKey: String = System.getenv("API_KEY") ?: "123456789"
    var baseUrl: String = System.getenv("BASE_URL") ?: "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
}