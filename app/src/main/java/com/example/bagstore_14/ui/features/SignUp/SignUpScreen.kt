package com.example.bagstore_14.ui.features.SignUp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagstore_14.R
import com.example.bagstore_14.ui.BagStoreUi
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.ui.theme.Shapes

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            BagStoreUi()
        }
    }
}

@Composable
fun SingUpScreen() {
    MainTextField(edtValue = "" , icon = R.drawable.ic_person , hint = "profile"){


    }
}
@Composable
fun MainTextField(
    edtValue:String,
    icon:Int,
    hint:String,
    onValueChanges:(String) ->Unit
){
    OutlinedTextField(label = { Text(hint)},
    value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint)},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 12.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(painterResource(icon),null)}

        )


}