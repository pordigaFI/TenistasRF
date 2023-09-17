package com.porfirio.practica1.Utilerias

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar

//Función para simplificar el uso de un Toast
fun Activity.Toast(text: String, length: Int=Toast.LENGTH_SHORT){
    Toast.makeText(this,text, length).show()
}

//Función para simplificar el uso de SnackBar
fun Activity.sbMessage(view: View, text: String, textColor: String?= "AzulTurquesa", bgColor: String?= "verdeLimon", length: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(view, text, length)
        .setTextColor(Color.parseColor(textColor))
        .setBackgroundTint(Color.parseColor(bgColor))
        .show()
}

//Función para ocultar el teclado virtual
fun View.hideKeyboard(){
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken,0)
}