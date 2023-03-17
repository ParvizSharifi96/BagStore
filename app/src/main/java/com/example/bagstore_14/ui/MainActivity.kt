package com.example.bagstore_14.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bagstore_14.di.myModules
import com.example.bagstore_14.model.repository.TokenInMemory
import com.example.bagstore_14.model.repository.user.UserRepository
import com.example.bagstore_14.ui.features.IntroScreen
import com.example.bagstore_14.ui.features.SignUp.SingUpScreen
import com.example.bagstore_14.ui.features.main.MainScreen
import com.example.bagstore_14.ui.features.signIn.SingInScreen
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.util.KEY_CATEGORY_ARG
import com.example.bagstore_14.util.KEY_PRODUCT_ARG
import com.example.bagstore_14.util.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)

            }) {
                MainAppTheme {
                    Surface(color = BackgroundMain, modifier = Modifier.fillMaxSize()) {
                        val userRepository: UserRepository = get()
                        userRepository.loadToken()
                        BagStoreUi()

                    }
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ) {
            BagStoreUi()
        }
    }
}


@Composable
fun BagStoreUi() {
    val navController = rememberNavController()
    KoinNavHost(
        navController = navController,
        startDestination = MyScreens.MainScreen.route
    ) {
        composable(MyScreens.MainScreen.route) {

            if (TokenInMemory.token !=null) {
                MainScreen()
            } else {
                IntroScreen()
            }

        }

        composable(
            route = MyScreens.ProductScreen.route + "/" + "{$KEY_PRODUCT_ARG}",
            arguments = listOf(navArgument(KEY_PRODUCT_ARG) {
                type = NavType.StringType
            })
        ) {
            ProductScreen(it.arguments!!.getString(KEY_PRODUCT_ARG, "null"))
        }

        composable(
            route = MyScreens.CategoryScreen.route + "/" + "{$KEY_CATEGORY_ARG}",
            arguments = listOf(navArgument(KEY_CATEGORY_ARG) {
                type = NavType.StringType
            })
        ) {
            CategoryScreen(it.arguments!!.getString(KEY_CATEGORY_ARG, "null"))
        }

        composable(MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }

        composable(MyScreens.CartScreen.route) {
            CartScreen()
        }

        composable(MyScreens.SignUpScreen.route) {
            SingUpScreen()
        }

        composable(MyScreens.SignInScreen.route) {
            SingInScreen()
        }


    }


}


@Composable
fun CartScreen() {

}

@Composable
fun ProfileScreen() {

}

@Composable
fun CategoryScreen(categoryName: String) {

}

@Composable
fun ProductScreen(productId: String) {

}

