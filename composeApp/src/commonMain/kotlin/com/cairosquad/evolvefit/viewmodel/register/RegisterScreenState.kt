package com.cairosquad.evolvefit.viewmodel.register

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.balance_trainer
import evolvefit.composeapp.generated.resources.bench
import evolvefit.composeapp.generated.resources.dumbbell
import evolvefit.composeapp.generated.resources.elastic_ball
import evolvefit.composeapp.generated.resources.exercise_ball
import evolvefit.composeapp.generated.resources.friday
import evolvefit.composeapp.generated.resources.jump_rope
import evolvefit.composeapp.generated.resources.monday
import evolvefit.composeapp.generated.resources.saturday
import evolvefit.composeapp.generated.resources.sunday
import evolvefit.composeapp.generated.resources.suspension_trainer
import evolvefit.composeapp.generated.resources.thursday
import evolvefit.composeapp.generated.resources.tuesday
import evolvefit.composeapp.generated.resources.wednesday
import org.jetbrains.compose.resources.StringResource

data class RegisterScreenState(
    val currentStep: Int = 1,
    val selectedHeight: Float = 0F,
    val selectedWeight: Float = 0F,
    val nextButtonEnabled: Boolean = true,
    val selectedGender: Gender? = null,
    val selectedMeasurementUnit: MeasurementUnit? = null,
    val selectedGoal: Goal? = null,
    val isWaterReminderEnabled: Boolean = false,
    val isWorkoutReminderEnabled: Boolean = false,
    val isBodyWeightReminderEnabled: Boolean = false,
    val isChallengesReminderEnabled: Boolean = false,
    val selectedWorkoutDays: List<WorkoutDay> = emptyList(),
    val selectedEquipments: List<Equipment> = emptyList()
) {
    enum class Gender {
        Female, Male
    }

    enum class MeasurementUnit {
        Metric, Imperial
    }

    enum class Goal {
        LoseWeight, GainWeight, StayInShape
    }

    enum class WorkoutDay(val resId: StringResource) {
        SUNDAY(Res.string.sunday),
        MONDAY(Res.string.monday),
        TUESDAY(Res.string.tuesday),
        WEDNESDAY(Res.string.wednesday),
        THURSDAY(Res.string.thursday),
        FRIDAY(Res.string.friday),
        SATURDAY(Res.string.saturday);
    }
    enum class Equipment(val resId: StringResource) {
        BALANCE_TRAINER(Res.string.balance_trainer),
        BENCH(Res.string.bench),
        DUMBBELL(Res.string.dumbbell),
        ELASTIC_BALL(Res.string.elastic_ball),
        EXERCISE_BALL(Res.string.exercise_ball),
        JUMP_ROPE(Res.string.jump_rope),
        SUSPENSION_TRAINER(Res.string.suspension_trainer)
    }
}
