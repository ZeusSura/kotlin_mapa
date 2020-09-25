package com.omarg.mapapplication.Utilidades

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.omarg.mapapplication.R

class UtilidadesUI {

    companion object {
        fun snackSuccessful(mensaje: String, vista: View, context: Context,duration: Int): Snackbar {
            val snackBar = Snackbar.make(vista, mensaje,duration)

            snackBar.setAction("Ok") { snackBar.dismiss() }
            snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            snackBar.setTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            val vistaSnackBar = snackBar.view
            vistaSnackBar.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorGreenSuccess
                )
            )
            return snackBar
        }

        fun snackError(mensaje: String, vista: View, context: Context,duration:Int): Snackbar {
            val snackBar = Snackbar.make(vista, mensaje, duration)

            snackBar.setAction("Ok") { snackBar.dismiss() }
            snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            snackBar.setTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            val vistaSnackBar = snackBar.view
            vistaSnackBar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorError))
            return snackBar
        }

        fun snackWarning(mensaje: String, vista: View, context: Context,duration: Int): Snackbar {
            val snackBar = Snackbar.make(vista, mensaje, duration)

            snackBar.setAction("Ok") { snackBar.dismiss() }
            snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            snackBar.setTextColor(ContextCompat.getColor(context, R.color.colorBlanco))
            val vistaSnackBar = snackBar.view
            vistaSnackBar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWarning))
            return snackBar
        }

    }

}