package com.example.bagstore_14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bagstore_14.ui.theme.MainAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainAppTheme { BagStoreUi() } }
    }
}


@Composable
fun BagStoreUi() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen()
        }

        composable(
            route = "productScreen/{productId}",
        arguments = listOf(navArgument("productId"){
            type= NavType.IntType
        })
        ) {
            ProductScreen(it.arguments!!.getInt("productId",-1))
        }

        composable(
            route = "categoryScreen",
        arguments = listOf(navArgument("categoryName"){
            type = NavType.StringType
        })
        ) {
            CategoryScreen(it.arguments!!.getString("categoryName","null"))
        }

        composable("profileScreen") {
            ProfileScreen()
        }

        composable("cartScreen") {
            CartScreen()
        }

        composable("singUpScreen") {
            SingUpScreen()
        }

        composable("singInScreen") {
            SingInScreen()
        }

        composable("introScreen") {
            IntroScreen()
        }

        composable("noInternetScreen") {
            NoInternetScreen()
        }


    }


}

@Composable
fun NoInternetScreen() {

}

@Composable
fun IntroScreen() {

}

@Composable
fun SingInScreen() {

}

@Composable
fun SingUpScreen() {

}

@Composable
fun CartScreen() {

}

@Composable
fun ProfileScreen() {

}

@Composable
fun CategoryScreen(categoryName : String) {

}

@Composable
fun ProductScreen(productId:Int) {

}

@Composable
fun MainScreen() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {
        BagStoreUi()
    }
}