package com.example.bagstore_14.model.data

data class AdsResponse(
    val success: Boolean,
    val ads: List<Ads>
)

data class Ads(
    val adId: String,
    val imageURL: String,
    val productId: String
)