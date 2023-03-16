package com.example.bagstore_14.ui.features.main

import android.app.Notification.BigPictureStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagstore_14.ui.CategoryScreen
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true)
@Composable
fun MainScreePreview(){

    MainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundMain
        ) {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen(){
    val uiController = rememberSystemUiController()
    SideEffect {uiController.setStatusBarColor(Color.White)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)

    ) {
         TopToolbar()

        CategoryBar()

        ProductSubject()
        ProductSubject()

        BigPictureTablighat()

        ProductSubject()
        ProductSubject()
    }

}

@Composable
fun TopToolbar() {

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        title = { Text(text = "Duni Bazaar") },
        actions = {

            IconButton(onClick = {}) {
                Icon(Icons.Default.ShoppingCart, null)
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.Person, null)
            }

        }
    )


}
//------------------------------------------------------------------------------

@Composable
fun CategoryBar() {

}
//------------------------------------------------------------------------------

@Composable
fun ProductSubject() {
}
//------------------------------------------------------------------------------

@Composable
fun BigPictureTablighat() {
}
//------------------------------------------------------------------------------
