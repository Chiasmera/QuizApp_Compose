package com.chiasmera.quizudfordring_compose.Controller

import androidx.navigation.NavType
import androidx.navigation.navArgument

class Destinations {

    interface Destination {
        val route: String

    }

    object CategoryViewDestination  : Destination {
        override val route = "categoryView"

    }

    object QuestionViewDestination  : Destination {
        override val route = "questionView"
        val argName = "index"
        val routeWithArgs = "${route}/{${argName}}"
        val arguments = listOf(
            navArgument(argName) { type = NavType.IntType}
        )

    }

}