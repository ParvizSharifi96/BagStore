package com.example.bagstore_14.ui.features.product

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bagstore_14.R
import com.example.bagstore_14.model.data.Comment
import com.example.bagstore_14.model.data.Product
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.ui.theme.Shapes
import com.example.bagstore_14.util.MyScreens
import com.example.bagstore_14.util.NetworkChecker
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel


@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {

    MainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundMain
        ) {
            ProductScreen("")
        }

    }

}

@Composable
fun ProductScreen(productId: String) {

    val context = LocalContext.current
    val viewModel = getNavViewModel<ProductViewModel>()
    viewModel.loadData(productId ,  NetworkChecker(context).isInternetConnected)
    val navigation = getNavController()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 58.dp)
        ) {

            ProductToolbar(
                productName = "Details",
                badgeNumber = 0,
                OnBackClicked = {
                    navigation.popBackStack()
                },
                OnCartClicked = {

                    if (NetworkChecker(context).isInternetConnected) {
                        navigation.navigate(MyScreens.CartScreen.route)
                    } else {
                        Toast.makeText(context, "Please connect to internet", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            )

            ProductItem(data = viewModel.thisProduct.value,
                comments = viewModel.comments.value,
                onCategoryClicked = {
                    navigation.navigate(MyScreens.CategoryScreen.route + "/" + it)
                }
            )

        }
        AddToCart()
    }
}

@Composable
fun ProductItem(data: Product, comments : List<Comment> ,onCategoryClicked: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        ProductDesign(data, onCategoryClicked)

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 14.dp, bottom = 14.dp)
        )
        ProductDetail(data , comments.size.toString())

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 14.dp, bottom = 4.dp)
        )



    }


}

@Composable
fun ProductDetail(data: Product, commentNumber: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_comment),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )

                Text(
                    text = "$commentNumber comments",
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 13.sp
                )

            }

            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_material),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )

                Text(
                    text = data.material,
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 13.sp
                )

            }

            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_sold),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )

                Text(
                    text = data.soldItem + " sold",
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 13.sp
                )

            }
        }

        Surface(
            modifier = Modifier
                .clip(Shapes.large)
                .align(Alignment.Bottom),
            color = Color.Blue
        ) {

            Text(
                text = data.tags,
                color = Color.White,
                modifier = Modifier.padding(6.dp),
                style = TextStyle(fontSize = 13.sp , fontWeight = FontWeight.Medium)
            )
        }

    }


}

@Composable
fun ProductDesign(data: Product, onCategoryClicked: (String) -> Unit) {
    AsyncImage(
        model = data.imgUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(Shapes.medium)

    )
    Text(
        modifier = Modifier.padding(top = 14.dp),
        text = data.name,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    )

    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = data.detailText,
        style = TextStyle(fontSize = 15.sp, textAlign = TextAlign.Justify)
    )

    TextButton(onClick = { onCategoryClicked.invoke(data.category) }) {
        Text(
            text = "#" + data.category,
            style = TextStyle(fontSize = 13.sp)
        )

    }

}


@Composable
fun ProductToolbar(
    productName: String,
    badgeNumber: Int,
    OnBackClicked: () -> Unit,
    OnCartClicked: () -> Unit
) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { OnBackClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        elevation = 2.dp,
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
        title = {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp),
                text = productName,
                textAlign = TextAlign.Center
            )
        },
        actions = {

            IconButton(
                modifier = Modifier.padding(end = 6.dp),
                onClick = { OnCartClicked.invoke() }
            ) {

                if (badgeNumber == 0) {
                    Icon(Icons.Default.ShoppingCart, null)
                } else {

                    BadgedBox(badge = { Badge { Text(badgeNumber.toString()) } }) {
                        Icon(Icons.Default.ShoppingCart, null)
                    }
                }
            }
        }
    )
}

@Composable
fun AddToCart() {

}
