package com.contents.data.model


import com.google.gson.annotations.SerializedName

data class GeocodeResponse(
    @SerializedName("addresses")
    val addresses: List<Address>,
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("status")
    val status: String
)

data class Address(
    @SerializedName("addressElements")
    val addressElements: List<AddressElement>,
    @SerializedName("englishAddress")
    val englishAddress: String,
    @SerializedName("x")
    val x: String,
    @SerializedName("y")
    val y: String
)

data class AddressElement(
    @SerializedName("code")
    val code: String,
    @SerializedName("longName")
    val longName: String,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("types")
    val types: List<String>
)