package com.shaikhabdulgani.moviedb.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screens {

    @Serializable
    data object Home : Screens

    @Serializable
    data class Detail(val id: String) : Screens

}
