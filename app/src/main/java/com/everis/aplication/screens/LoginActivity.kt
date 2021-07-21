package com.everis.aplication.screens

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.everis.aplication.ui.theme.AplicationTheme
import com.everis.aplication.ui.theme.Shapes
import com.everis.domain.entities.CustomError
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModel(clazz = LoginViewModel::class)
    //private val loginViewModel: LoginViewModel by viewModel()
    lateinit var email:String
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen()
                }
            }
        }

    }


    @Composable
    fun LoginScreen() {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(128.dp))
            EmailTextField()
            Spacer(modifier = Modifier.height(4.dp))
            PasswordTextField()
            Spacer(modifier = Modifier.height(64.dp))
            ButtonLogin()
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(128.dp))
            /*ButtonToRegister(onClick = {
                mContext.startActivity(Intent(mContext,RegisterActivity::class.java))
            })*/
        }
    }

    @Preview
    @Composable
    private fun EmailTextField() {
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
    }

    @Preview
    @Composable
    private fun PasswordTextField() {
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
    }

    @Preview
    @Composable
    private fun ButtonLogin() {
        Button(
            onClick = { loginViewModel.doLogin(email, password) },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 1.dp),
            shape = Shapes.large
        ) {
            Text("Login")
        }
    }


    private fun iniObservers() {
        loginViewModel.loadingLiveData.observe(this, Observer {
            Log.e("LoginActivity", "$it")
        })
        loginViewModel.errorLiveData.observe(this, Observer { showError(it) })
        loginViewModel.userLiveData.observe(this, Observer {
            Log.e("LoginActivity", "$it")

        })
    }

    private fun showError(error: CustomError) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

}