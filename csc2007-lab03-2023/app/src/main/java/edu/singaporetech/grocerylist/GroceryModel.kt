package edu.singaporetech.grocerylist

import android.os.Parcel
import android.os.Parcelable

data class GroceryModel(val title: String?, var checked: Boolean?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeByte(if (checked == true) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GroceryModel> {
        override fun createFromParcel(parcel: Parcel): GroceryModel {
            return GroceryModel(parcel)
        }

        override fun newArray(size: Int): Array<GroceryModel?> {
            return arrayOfNulls(size)
        }
    }
}
