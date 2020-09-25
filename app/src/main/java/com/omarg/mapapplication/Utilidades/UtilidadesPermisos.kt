package com.omarg.mapapplication.Utilidades

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class UtilidadesPermisos {


    companion object {

        //Permisos de ubicación
        val namePermisosFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val namePermisosCorse = android.Manifest.permission.ACCESS_COARSE_LOCATION

        //Metodos para los permisos de ubicación
        fun checkPermissionLocation(context: Context): Boolean {
            val isPermissionFineLocation = ActivityCompat.checkSelfPermission(
                context,
                namePermisosFine
            ) == PackageManager.PERMISSION_GRANTED
            val isPermissionCoarseLocation = ActivityCompat.checkSelfPermission(
                context,
                namePermisosCorse
            ) == PackageManager.PERMISSION_GRANTED
            return isPermissionCoarseLocation && isPermissionFineLocation
        }

        fun pedirPermisosLocation(activity: Activity): Int {
            val LOCATION_PERMISION_REQUEST_CODE = 1000
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(namePermisosFine, namePermisosCorse),
                LOCATION_PERMISION_REQUEST_CODE
            )
            return LOCATION_PERMISION_REQUEST_CODE
        }

        //Permisos lecuta y escrituta
        val namePermisoReadMemory = android.Manifest.permission.READ_EXTERNAL_STORAGE
        val namePermisosWriteMemory = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

        //Metodos para permisos de escritura y lectura
        fun checkPermissionStorage(context: Context): Boolean {

            val isPermissionReadStorage = ActivityCompat.checkSelfPermission(
                context,
                namePermisoReadMemory
            ) == PackageManager.PERMISSION_GRANTED
            val isPermissionWriteStorage = ActivityCompat.checkSelfPermission(
                context,
                namePermisosWriteMemory
            ) == PackageManager.PERMISSION_GRANTED
            return isPermissionReadStorage && isPermissionWriteStorage
        }
        fun pedirPermisosStorage(activity: Activity): Int {
            val STORAGE_PERMISION_REQUEST_CODE = 1000
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(namePermisoReadMemory, namePermisosWriteMemory),
                STORAGE_PERMISION_REQUEST_CODE
            )
            return STORAGE_PERMISION_REQUEST_CODE
        }
    }
}