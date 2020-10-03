package com.rcacao.localsearch.data.model

import com.google.gson.annotations.SerializedName

class Provider {

    val features: List<String> = emptyList()
    val id: String = ""
    val type: String = ""
    val name: String = ""
    val specialties: List<String> = emptyList()

    @SerializedName("sub_specialties")
    val subSpecialties: List<String> = emptyList()

    val addresses: List<Address> = emptyList()

    @SerializedName("simplified_addresses")
    val simplifiedAddresses: List<Address> = emptyList()

    val site: String = ""
    val crm: String = ""
    val cnpj: String = ""
    val phones: List<Phone> = emptyList()

    @SerializedName("working_hours")
    val workingHours: WorkingHours = WorkingHours()

    val qualifications: List<String> = emptyList()

    @SerializedName("image_url")
    val imageUrl: String = ""

    val education: List<String> = emptyList()
    val curiosity: String = ""

}