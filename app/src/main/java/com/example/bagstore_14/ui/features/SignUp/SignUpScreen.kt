package com.example.bagstore_14.ui.features.SignUp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagstore_14.R
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.Blue
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.ui.theme.Shapes

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain ,
            modifier = Modifier.wrapContentSize()
        ) {
            MainCardView{

            }
        }
    }
}

@Composable
fun SingUpScreen() {

}
@Composable
fun MainCardView(SignUpEvent:()->Unit){

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password= remember { mutableStateOf("") }
    val confirmPassword= remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = 10.dp,
        shape = Shapes.medium
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
                text = "Sign Up",
                style = TextStyle(color = Blue, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            )
            MainTextField(name.value,R.drawable.ic_person , "Your Full Name"){name.value = it}
            MainTextField(email.value,R.drawable.ic_email , "Email"){email.value = it}
            MainTextField(password.value,R.drawable.ic_password , "Password"){password.value = it}
            MainTextField(confirmPassword.value,R.drawable.ic_password , "Confirm Password"){confirmPassword.value = it}

            Button(onClick = SignUpEvent, modifier = Modifier.padding(top = 28.dp, bottom = 8.dp)) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Register Account"
                )
            }

            Row(modifier = Modifier.padding(bottom = 18.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
                Text("Already have an account?")
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick ={} ) {
                    Text("Log In" , color = Blue)
                    
                }
            }
        }
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