package com.cope.core.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Oscar Gallon on 2019-06-12.
 */
data class ViewCopeContent(
    val id: String,
    val text: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewCopeContent> {
        override fun createFromParcel(parcel: Parcel): ViewCopeContent {
            return ViewCopeContent(parcel)
        }

        override fun newArray(size: Int): Array<ViewCopeContent?> {
            return arrayOfNulls(size)
        }
    }
}
