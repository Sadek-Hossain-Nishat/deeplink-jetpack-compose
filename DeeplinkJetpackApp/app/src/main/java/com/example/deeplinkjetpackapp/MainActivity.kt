package com.example.deeplinkjetpackapp

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.deeplinkjetpackapp.ui.theme.DeeplinkJetpackAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeeplinkJetpackAppTheme {

                /*** deeplink second part with out any third party dependency  ***/


                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Button(onClick = {
                                navController.navigate("detail")
                            }) {
                                Text(text = "To Detail")
                            }


                        }
                    }


                    composable("detail", deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "https://www.github.com/{id}"
                            action = Intent.ACTION_VIEW
                        }
                    ),
                        arguments = listOf(
                            navArgument("id") {

                                type = NavType.StringType
                                defaultValue = ""

                            }
                        )


                    ) {

                            entry ->
                        val id = entry.arguments?.getString("id")


                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){


                            Text(text = "The id is $id")

                        }
                    }

                }



            }
        }
    }
}

/***  deep link first part -> deeplink with third party dependency  ***/
/***
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.deeplinkjetpackapp.ui.theme.DeeplinkJetpackAppTheme

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContent {
DeeplinkJetpackAppTheme {
val navController = rememberNavController()
NavHost(navController = navController, startDestination = "home") {

composable("home") {
Box(
modifier = Modifier.fillMaxSize(),
contentAlignment = Alignment.Center
) {

Button(onClick = {
navController.navigate("detail")
}) {
Text(text = "To Detail")
}


}
}


composable("detail", deepLinks = listOf(
navDeepLink {
uriPattern = "https://www.github.com/{id}"
action = Intent.ACTION_VIEW
}
),
arguments = listOf(
navArgument("id") {

type = NavType.StringType
defaultValue = ""

}
)


) {

entry ->
val id = entry.arguments?.getString("id")


Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){


Text(text = "The id is $id")

}
}

}

}
}
}
}


 */