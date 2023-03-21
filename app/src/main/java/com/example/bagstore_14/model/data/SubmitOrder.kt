package com.example.bagstore_14.model.data

data class SubmitOrder(
    val success: Boolean ,
    val orderId: Int,
    val paymentLink :String
)