package com.example.bagstore_14.ui.features.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bagstore_14.R
import com.example.bagstore_14.model.data.Ads
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.ui.theme.*
import com.example.bagstore_14.util.CATEGORY
import com.example.bagstore_14.util.NetworkChecker
import com.example.bagstore_14.util.TAGS
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavViewModel
import org.koin.core.parameter.parametersOf
import javax.security.auth.Subject


@Preview(showBackground = true)
@Composable
fun MainScreePreview() {

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
fun MainScreen() {
    val context = LocalContext.current
    val uiController = rememberSystemUiController()
    SideEffect { uiController.setStatusBarColor(Color.White) }
    val viewModel = getNavViewModel<MainViewModel>(
        parameters = { parametersOf(NetworkChecker(context).isInternetConnected) })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)

    ) {

        if (viewModel.showProgressBar.value) {

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Blue

            )
        }

        TopToolbar()
        CategoryBar(CATEGORY)

        val productDataState = viewModel.dataProducts
        val adsDataState = viewModel.dataAds

        ProductSubjectList(TAGS, productDataState.value, adsDataState.value)


    }

}

//------------------------------------------------------------------------------
@Composable
fun ProductSubjectList(tags: List<String>, products: List<Product>, ads: List<Ads>) {
    val content = LocalContext.current
    if (products.isEmpty()){
        Toast.makeText(content, "Please connect to internet", Toast.LENGTH_SHORT).show()
    }else{
    Column {
        tags.forEachIndexed { it, _ ->
            val withTagData = products.filter { product -> product.tags == tags[it] }
            ProductSubject(tags[it], withTagData.shuffled())
            if (ads.size >= 2)
            if (it == 1 || it== 2 ){
                BigPictureTablighat(ads[it - 1])
        }
        }
    }
    }

}


@Composable
fun TopToolbar() {

    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        title = { Text(text = "BagStore-14") },
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
fun CategoryBar(categoryList: List<Pair<String, Int>>) {

    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(end = 16.dp),
    ) {
        items(categoryList.size) {
            CategoryItem(categoryList[it])
        }
    }


}

@Composable
fun CategoryItem(subject: Pair<String, Int>) {

    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            shape = Shapes.medium,
            color = CardViewBackground
        ) {

            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = subject.second),
                contentDescription = null
            )

        }
        Text(
            text = subject.first,
            modifier = Modifier.padding(top = 4.dp),
            style = TextStyle(color = Color.Gray)
        )
    }

}
//------------------------------------------------------------------------------

@Composable
fun ProductSubject(subject: String, data: List<Product>) {

    Column(
        modifier = Modifier.padding(top = 32.dp),

        ) {

        Text(
            text = subject,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.h6
        )
        ProductBar(data)

    }


}

@Composable
fun ProductBar(data: List<Product>) {
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        items(data.size) {
            ProductItem(data[it])
        }
    }
}

@Composable
fun ProductItem(product: Product) {

    Card(
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { },
        elevation = 4.dp,
        shape = Shapes.large
    ) {

        Column {

            AsyncImage(
                model = product.imgUrl,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Text(
                    text = product.name,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = product.price + "Tomans",
                    style = TextStyle(fontSize = 14.sp)
                )

                Text(
                    text = product.soldItem + "Sold",
                    style = TextStyle(color = Color.Gray, fontSize = 13.sp)
                )

            }
        }
    }
}

//------------------------------------------------------------------------------

@Composable
fun BigPictureTablighat(ads :Ads) {


    AsyncImage(
        model = ads.imageURL,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
            .clip(Shapes.medium)
            .clickable { },
        contentDescription = null,
        contentScale = ContentScale.Crop
    )


}
//------------------------------------------------------------------------------
