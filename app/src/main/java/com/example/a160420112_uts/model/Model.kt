package com.example.a160420112_uts.model

import com.google.gson.annotations.SerializedName

data class Donations(
    @SerializedName("id")
    val id:Int?,

    @SerializedName("totalTerkumpul")
    val totalTerkumpul:Int?,

    @SerializedName("target")
    val target:Int?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("keterangan")
    val keterangan:String?,

    @SerializedName("image")
    val image:String?
)
data class News(
    @SerializedName("idBerita")
    val idBerita:Int?,

    @SerializedName("namaBerita")
    val namaBerita:String?,

    @SerializedName("deskripsiNews")
    val deskripsiNews:String?,

    @SerializedName("urlNews")
    val urlNews:String?,

    @SerializedName("imageBerita")
    val imageBerita:String?
)
data class Histories(
    @SerializedName("idHistory")
    val idHistory: Int?,

    @SerializedName("namaHistory")
    val namaHistory: String?,

    @SerializedName("nominalDonasi")
    val nominalDonasi: Int?,
    @SerializedName("tanggal")
    val tanggal: String?,

    @SerializedName("namaDonasiH")
    val namaDonasiH: String?,
)
data class Profiles(
    @SerializedName("nameProfile")
    val nameProfile:String?,

    @SerializedName("email")
    val email:String?,

    @SerializedName("noTelp")
    val noTelp:String?,

    @SerializedName("status")
    val status:String?,

    @SerializedName("imageProfile")
    val imageProfile:String?
)
