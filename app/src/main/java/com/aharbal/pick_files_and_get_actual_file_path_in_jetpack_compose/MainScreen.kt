package com.aharbal.pick_files_and_get_actual_file_path_in_jetpack_compose

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult =
        {
           viewModel.onFilePathsListChange(it,context)

        })
    val state = viewModel.state

    SideEffect {
        permissionState.launchPermissionRequest()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.76f)){
            if (state.filePaths.isEmpty()){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    Text(text = "Pick some files")
                }
            }
            if (state.filePaths.isNotEmpty()){
                LazyColumn{
                    items(state.filePaths){path ->
                        Text(text = path)
                        Spacer(modifier = Modifier.height(9.dp))

                    }
                }
            }
        }
        Button(onClick = {
            if (permissionState.status.isGranted) {
                filePickerLauncher.launch("*/*")
            }
            else{
                permissionState.launchPermissionRequest()
            }
        }) {
            Text(text = "Pick files")
        }

    }




}