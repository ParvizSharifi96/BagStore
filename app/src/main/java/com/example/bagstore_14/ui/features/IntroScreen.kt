package com.example.bagstore_14.ui.features

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.bagstore_14.ui.BagStoreUi
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {

        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            IntroScreen()
        }


    }
}

@Composable
fun IntroScreen() {

}