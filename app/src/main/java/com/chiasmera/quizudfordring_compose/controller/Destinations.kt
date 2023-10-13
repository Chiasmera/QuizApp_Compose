package com.chiasmera.quizudfordring_compose.controller

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Contains the navigation information for the app.
 */
class Destinations {

    /**
     * Contract that all destinations in the app must implement
     */
    interface Destination {
        val route: String

    }

    /**
     * Destination for the view that shows all categories
     */
    object CategoryViewDestination : Destination {
        override val route = "categoryView"

    }

    /**
     * Destination for the view that shows a single question
     */
    object QuestionViewDestination : Destination {
        override val route = "questionView"
        val argName = "index"
        val routeWithArgs = "${route}/{${argName}}"
        val arguments = listOf(navArgument(argName) { type = NavType.IntType })

    }

}