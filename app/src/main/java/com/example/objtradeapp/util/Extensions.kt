package com.example.objtradeapp.util

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import com.google.gson.Gson

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}



data class UribyString(val id: String, val uri: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UribyString> {
        override fun createFromParcel(parcel: Parcel): UribyString {
            return UribyString(parcel)
        }

        override fun newArray(size: Int): Array<UribyString?> {
            return arrayOfNulls(size)
        }
    }
}

class AssetParamType : NavType<UribyString>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UribyString? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): UribyString {
        return Gson().fromJson(value, UribyString::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UribyString) {
        bundle.putParcelable(key, value)
    }
}

