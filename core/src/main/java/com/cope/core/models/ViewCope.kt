package com.cope.core.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
data class ViewCope(
    val id: String,
    val url: String,
    val title: String,
    val createdAt: Date,
    val updateAt: Date,
    val content: List<ViewCopeContent>,
    val icon: String? = null
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        Date(parcel.readLong()),
        Date(parcel.readLong()),
        parcel.createTypedArrayList(ViewCopeContent),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeLong(createdAt.time)
        parcel.writeLong(updateAt.time)
        parcel.writeTypedList(content)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewCope> {
        override fun createFromParcel(parcel: Parcel): ViewCope {
            return ViewCope(parcel)
        }

        override fun newArray(size: Int): Array<ViewCope?> {
            return arrayOfNulls(size)
        }
    }

}
