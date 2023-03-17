package com.example.bagstore_14.ui.features.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.getColumnIndex
import coil.compose.AsyncImage
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.ui.BagStoreUi
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.ui.theme.Shapes
import com.example.bagstore_14.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MainAppTheme {
//        Surface(
//            color = BackgroundMain,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            BagStoreUi()
//        }
//    }
//}


@Composable
fun CategoryScreen(categoryName: String) {
    val viewModel = getNavViewModel<CategoryViewModel>()
    val navigation = getNavController()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CategoryToolbar(categoryName)
        val data = viewModel.dataProducts
        CategoryList(data.value) {
            navigation.navigate(MyScreens.ProductScreen.route + "/" + it)
        }
    }

}

@Composable
fun CategoryItem(data: Product, onProductClicked: (String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onProductClicked.invoke(data.productId) },
        elevation = 4.dp,
        shape = Shapes.large
    ) {
        Column {
            AsyncImage(
                model = data.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Text(
                        text = data.name,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = data.price + "Tomans",
                        style = TextStyle(fontSize = 14.sp)
                    )

                }

                Surface(
                    modifier = Modifier.padding(bottom = 8.dp , end = 8.dp)
                        .align(Alignment.Bottom)
                        .clip(Shapes.large)
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = data.soldItem + "Sold",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }


}


@Composable
fun CategoryList(data: List<Product>, onProductClicked: (String) -> Unit) {

}


@Composable
fun CategoryToolbar(categoryName: String) {

}
