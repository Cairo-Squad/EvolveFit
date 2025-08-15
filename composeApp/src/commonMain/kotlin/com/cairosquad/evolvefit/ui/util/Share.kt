@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.cairosquad.evolvefit.ui.util

import org.jetbrains.compose.resources.StringResource

expect object Share {
    fun shareOnMessenger(url: String, onDismiss: () -> Unit)
    fun shareOnWhatsApp(url: String, onDismiss: () -> Unit)
    fun shareOnTelegram(url: String, onDismiss: () -> Unit)
    fun shareOnInstagram(url: String, onDismiss: () -> Unit)
    fun shareOnFacebook(url: String, onDismiss: () -> Unit)
    fun shareOnX(url: String, onDismiss: () -> Unit)
    fun copyLink(workoutUrl: String, onDismiss: (StringResource, Boolean) -> Unit)
}