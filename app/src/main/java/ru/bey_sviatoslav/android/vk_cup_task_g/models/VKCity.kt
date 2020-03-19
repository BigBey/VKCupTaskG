package ru.bey_sviatoslav.android.vk_cup_task_g.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup

class VKCity(
    val id: Int = 0,
    val title : String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKCity> {
        override fun createFromParcel(parcel: Parcel): VKCity {
            return VKCity(parcel)
        }

        override fun newArray(size: Int): Array<VKCity?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
                = VKCity(id = json.optInt("id", 0),
            title = json.optString("title", ""))
    }
}