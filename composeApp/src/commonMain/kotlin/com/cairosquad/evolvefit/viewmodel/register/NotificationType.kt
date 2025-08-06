package com.cairosquad.evolvefit.viewmodel.register

sealed class NotificationType {
    object Workout : NotificationType()
    object Water : NotificationType()
    object BodyWeight : NotificationType()
    object Challenges : NotificationType()
}