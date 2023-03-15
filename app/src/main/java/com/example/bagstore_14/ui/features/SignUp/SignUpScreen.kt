package com.example.bagstore_14.ui.features.SignUp

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bagstore_14.R
import com.example.bagstore_14.ui.theme.BackgroundMain
import com.example.bagstore_14.ui.theme.Blue
import com.example.bagstore_14.ui.theme.MainAppTheme
import com.example.bagstore_14.ui.theme.Shapes
import com.example.bagstore_14.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            SingUpScreen()
        }
    }
}

@Composable
fun SingUpScreen() {

    val navigation = getNavController()
    val viewModel = getNavViewModel<SignUpViewModel>()


     Box {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .background(Blue)
        )
         Column(
             modifier = Modifier
                 .fillMaxWidth()
                 .fillMaxHeight(0.95f),
             verticalArrangement = Arrangement.SpaceEvenly,
             horizontalAlignment = Alignment.CenterHorizontally

         ) {

             IconApp()
             MainCardView(navigation,viewModel){
                 viewModel.signUpUser()

             }
         }
     }

}

@Composable
fun IconApp(){
    Surface(
      modifier = Modifier
          .clip(CircleShape)
          .size(64.dp)
    ) {
        Image(
            modifier = Modifier.padding(14.dp),
            painter = painterResource(id = R.drawable.ic_icon_app) ,
            contentDescription =null )

    }


}

@Composable
fun MainCardView(navigation:NavController, viewModel: SignUpViewModel ,SignUpEvent:()->Unit){

    val name = viewModel.name.observeAsState("")
    val email = viewModel.email.observeAsState("")
    val password= viewModel.password.observeAsState("")
    val confirmPassword= viewModel.confirmPassword.observeAsState("")


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
            MainTextField(name.value,R.drawable.ic_person , "Your Full Name"){viewModel.name.value = it}
            MainTextField(email.value,R.drawable.ic_email , "Email"){viewModel.email.value = it}
            PasswordTextField(password.value,R.drawable.ic_password , "Password"){viewModel.password.value = it}
            PasswordTextField(confirmPassword.value,R.drawable.ic_password , "Confirm Password"){viewModel.confirmPassword.value = it}

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
                TextButton(onClick ={
                    navigation.navigate(MyScreens.SignInScreen.route){

                popUpTo(MyScreens.SignUpScreen.route){
                    inclusive = true
                }

                } } ) {
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

@Composable
fun PasswordTextField(edtValue:String, icon:Int, hint:String, onValueChanges:(String) ->Unit){

    val passwordVisible = remember { mutableStateOf(false) }


    OutlinedTextField(label = { Text(hint)},
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint)},
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 12.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(painterResource(icon),null)},
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            
            val image = if (passwordVisible.value) painterResource(id = R.drawable.ic_visible) else
                painterResource(R.drawable.ic_invisible)

            Icon(painter = image, contentDescription = null,
            modifier = Modifier.clickable { passwordVisible.value= !passwordVisible.value })
            
        }


    )


}