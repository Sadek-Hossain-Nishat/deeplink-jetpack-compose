package com.example.deeplinkapp2

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button

import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.core.app.TaskStackBuilder
import com.example.deeplinkapp2.ui.theme.DeeplinkApp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeeplinkApp2Theme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Button(onClick = {

                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.github.com/Sadek-Hossain-Nishat"))
                        val pendingIntent = TaskStackBuilder.create(applicationContext).run {
                            addNextIntentWithParentStack(intent)

                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

                                getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                                            or PendingIntent.FLAG_IMMUTABLE
                                )

                            } else {
                                getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT

                                )
                            }

                        }

                        pendingIntent?.send()

                    }) {

                        Text(text = "Triggger deeplink")

                    }
                }



            }
        }
    }
}

