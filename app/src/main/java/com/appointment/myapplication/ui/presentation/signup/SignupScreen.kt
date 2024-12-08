package com.appointment.myapplication.ui.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appointment.myapplication.key.R
import com.appointment.myapplication.util.LoginUiEvent
import ibtikar.tania.user.ui.viewmodels.SingupViewModel


@Composable
fun SignupScreen(
    signupViewModel: SingupViewModel = hiltViewModel(),
    successfulLogin: () -> Unit

) {


    val signupState by signupViewModel.signupState

    if (signupState.isLoginSuccessful) {
        LaunchedEffect(key1 = true) {
            successfulLogin.invoke()
        }
    }else {
        InputUICorporate(
            username = signupState.emailOrMobile,
            onUserNameChange = { inputString ->
                signupViewModel.eventListener(
                    signupUiEvent = LoginUiEvent.EmailOrMobileChanged(
                        inputString
                    )
                )
                //phoneNumber = it
            },
            password = signupState.password,
            onPasswordChange = { inputString ->
                signupViewModel.eventListener(
                    signupUiEvent = LoginUiEvent.PasswordChanged(
                        inputString
                    )
                )
            },
            showErrorOnName = signupState.errorState.emailOrMobileErrorState.hasError,
            showErrorOnPassword = signupState.errorState.passwordErrorState.hasError,
            onSignupBtnClick = {
                signupViewModel.eventListener(signupUiEvent = LoginUiEvent.Submit)
            },
            accountAlreadyCreated = {
            }
        )
    }


}


@Composable
private fun InputUICorporate(
    username: String,
    onUserNameChange: (String) -> Unit,
    password: String,
    showErrorOnName: Boolean, // Pass showError as a parameter
    showErrorOnPassword: Boolean, // Pass showError as a parameter
    onPasswordChange: (String) -> Unit,
    onSignupBtnClick: () ->Unit,
    accountAlreadyCreated: () -> Unit
) {

    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.light_gray))

    ) {

        Text(text = stringResource(id = R.string.login),
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            style =TextStyle(
                color = colorResource(id = R.color.dark_blue), // Set color from colors.xml
                fontSize = 25.sp,                        // Set text size
                fontWeight = FontWeight.Bold,             // Set text to bold
            )

        )

        Row (modifier = Modifier.weight(0.08f).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // Center the content horizontally
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.tanialogo),
                contentDescription = "Description of the image",
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp), // Example size, you can adjust it
                contentScale = ContentScale.Crop // Adjust the content scale if needed
            )
        }
        TextField(
            value = username,
            onValueChange = onUserNameChange,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 10.dp,
                    start = 25.dp,
                    end = 25.dp
                )
                .focusRequester(focusRequester)
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)),
            placeholder = { Text("Username") },
            singleLine = true,
            textStyle = TextStyle(fontSize = 10.sp),
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = colorResource(id = R.color.light_gray),
                focusedContainerColor = colorResource(id = R.color.light_gray),
                unfocusedContainerColor = colorResource(id = R.color.light_gray),
                disabledContainerColor = colorResource(id = R.color.light_gray), // Ensures the disabled state is also white
                errorContainerColor = colorResource(id = R.color.light_gray),    // Ensures the error state is also white
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

            )

        if (showErrorOnName) {
            Text(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 16.dp,
                        start = 25.dp,
                        end = 25.dp
                    ),
                text = "Username can't be empty",
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }


        TextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp,
                    start = 25.dp,
                    end = 25.dp
                )
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
                .focusRequester(focusRequester),
            placeholder = { Text("Password") },
            singleLine = true,
            textStyle = TextStyle(fontSize = 15.sp),
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = colorResource(id = R.color.light_gray),
                focusedContainerColor = colorResource(id = R.color.light_gray),
                unfocusedContainerColor = colorResource(id = R.color.light_gray),
                disabledContainerColor = colorResource(id = R.color.light_gray), // Ensures the disabled state is also white
                errorContainerColor = colorResource(id = R.color.light_gray),    // Ensures the error state is also white
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            //isError = showErrorOnPassword,
            trailingIcon = {
                if (showErrorOnPassword) {
                    Icon(
                        Icons.Filled.ErrorOutline,
                        contentDescription = "Error",
                        tint = Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            visualTransformation = PasswordVisualTransformation() // Add this line

        )
        if (showErrorOnPassword) {
            Text(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 25.dp
                    ),
                text = "Password can't be empty",
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = androidx.compose.ui.Modifier.weight(0.02f)) // Push content to the bottom

        CorporateLoginBtn{
            onSignupBtnClick()
        }

        Spacer(modifier = androidx.compose.ui.Modifier.weight(0.002f)) // Push content to the bottom

        AccountAlreadyCreated (text = AnnotatedString(stringResource(id = R.string._string_login_as_corporate_user))){
            accountAlreadyCreated()
        }
    }
}

@Composable
private fun CorporateLoginBtn(onLoginClick : () ->Unit ){
    Button(
        onClick = {
            onLoginClick()
        },
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 25.dp,
                end = 25.dp
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            ), // Add shadow modifier,
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange_color),
            contentColor = colorResource(
                id = R.color.white
            ),
            disabledContainerColor = colorResource(id = R.color.gray_color),
            disabledContentColor = colorResource(
                id = R.color.black
            )
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        androidx.compose.material3.Text(
            modifier = androidx.compose.ui.Modifier
                .padding(
                    start = 35.dp,
                    end = 35.dp,
                    top = 10.dp,
                    bottom = 10.dp
                ),
            text = stringResource(id = R.string.login),
            style = TextStyle(fontSize = 20.sp)

        )
    }
}



@Composable
private fun AccountAlreadyCreated(text: AnnotatedString, loginAsCorporateUser: () -> Unit) {
    ClickableText(
        onClick = {  },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center), // Center horizontally
        text = text,
        style = TextStyle(
            color = colorResource(id = R.color.dark_blue_new), // Set color from colors.xml
            fontWeight = FontWeight.Bold
        )
    )
}

@Preview
@Composable
fun PreviewSignup(){
    InputUICorporate(
        username = "",
        onUserNameChange = {},
        password = "",
        showErrorOnName = true,
        showErrorOnPassword = true,
        onPasswordChange = {  },
        onSignupBtnClick = {},
        accountAlreadyCreated = {}
        )
}