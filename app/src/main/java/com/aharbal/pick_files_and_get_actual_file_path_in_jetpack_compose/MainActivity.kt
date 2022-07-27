package com.aharbal.pick_files_and_get_actual_file_path_in_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.aharbal.pick_files_and_get_actual_file_path_in_jetpack_compose.ui.theme.Pick_FIles_And_Get_Actual_File_Path_In_Jetpack_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pick_FIles_And_Get_Actual_File_Path_In_Jetpack_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    MainScreen()
                }
            }
        }
    }
}

