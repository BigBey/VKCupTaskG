package ru.bey_sviatoslav.android.vk_cup_task_g.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class VKProduct(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title : String = "",
    val description: String = "",
    val price: Int,
    val thumbPhoto : String = "") : Parcelable {

        constructor(parcel: Parcel) : this(
        parcel.readInt(),
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeInt(ownerId)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeInt(price)
            parcel.writeString(thumbPhoto)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<VKProduct> {
            override fun createFromParcel(parcel: Parcel): VKProduct {
                return VKProduct(parcel)
            }

            override fun newArray(size: Int): Array<VKProduct?> {
                return arrayOfNulls(size)
            }

            fun parse(json: JSONObject)
                    = VKProduct(id = json.optInt("id", 0),
                ownerId = json.optInt("owner_id", 0),
                title = json.optString("title", ""),
                description = json.optString("description", ""),
                price = json.optJSONObject("price")!!.optInt("amount", 0)/100,
                thumbPhoto = json.optString("thumb_photo", ""))
        }
}