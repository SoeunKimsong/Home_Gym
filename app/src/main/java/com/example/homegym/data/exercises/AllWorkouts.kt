package com.example.homegym.data.exercises

object AllWorkouts {

    val absWorkout = Workout(
        1,
        "Abs Workout",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[1],
            WorkoutExercises.allExercises[2],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[4],
            WorkoutExercises.allExercises[5],
            WorkoutExercises.allExercises[6],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[9],
            WorkoutExercises.allExercises[10],
            WorkoutExercises.allExercises[11],
            WorkoutExercises.allExercises[12],
            WorkoutExercises.allExercises[35]
        ),
        80
    )
    val chestWorkout = Workout(
        2,
        "Chest Workout",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[13],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[15],
            WorkoutExercises.allExercises[16],
            WorkoutExercises.allExercises[17],
            WorkoutExercises.allExercises[18],
            WorkoutExercises.allExercises[19],
            WorkoutExercises.allExercises[20],
            WorkoutExercises.allExercises[21],
            WorkoutExercises.allExercises[32]),
        60
    )
    val armsWorkout = Workout(
        3,
        "Arms Workout",
        listOf(
            WorkoutExercises.allExercises[22],
            WorkoutExercises.allExercises[23],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[24],
            WorkoutExercises.allExercises[25],
            WorkoutExercises.allExercises[26],
            WorkoutExercises.allExercises[13],
            WorkoutExercises.allExercises[27],
            WorkoutExercises.allExercises[28],
            WorkoutExercises.allExercises[29]),
        50
    )

    // Burner Workout
    val bellyFatBurnerWorkout = Workout(
        4,
        "Belly Fat Burner",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[5],
            WorkoutExercises.allExercises[5],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[24],
            WorkoutExercises.allExercises[24], WorkoutExercises.allExercises[3], WorkoutExercises.allExercises[3], WorkoutExercises.allExercises[35], WorkoutExercises.allExercises[34]),
        80,
    )
    val rippedVAbsWorkout = Workout(
        5,
        "Ripped-V Abs",
        listOf(
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[4],
            WorkoutExercises.allExercises[4],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[6],
            WorkoutExercises.allExercises[6], WorkoutExercises.allExercises[11], WorkoutExercises.allExercises[11], WorkoutExercises.allExercises[35], WorkoutExercises.allExercises[34]),
        75,
    )
    val getRidManBoobWorkout = Workout(
        6,
        "Get Rid of Man Boob",
        listOf(
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[25],
            WorkoutExercises.allExercises[25],
            WorkoutExercises.allExercises[16],
            WorkoutExercises.allExercises[16],
            WorkoutExercises.allExercises[13],
            WorkoutExercises.allExercises[13],
            WorkoutExercises.allExercises[19],
            WorkoutExercises.allExercises[20], WorkoutExercises.allExercises[19], WorkoutExercises.allExercises[20], WorkoutExercises.allExercises[32], WorkoutExercises.allExercises[35]),
        60
    )

    // Quarantine Workout
    val immunityBoosterWorkout = Workout(
        7,
        "Immunity Booster",
        listOf(
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[6],
            WorkoutExercises.allExercises[6],
            WorkoutExercises.allExercises[11],
            WorkoutExercises.allExercises[11], WorkoutExercises.allExercises[21], WorkoutExercises.allExercises[21], WorkoutExercises.allExercises[29], WorkoutExercises.allExercises[29]),
        50
    )
    val athletesChoicesWorkout = Workout(
        8,
        "Athletes' Choices",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[2],
            WorkoutExercises.allExercises[2],
            WorkoutExercises.allExercises[16],
            WorkoutExercises.allExercises[16], WorkoutExercises.allExercises[10], WorkoutExercises.allExercises[10], WorkoutExercises.allExercises[29], WorkoutExercises.allExercises[29]),
        55
    )
    val stayInShapeWorkout = Workout(
        9,
        "Stay In Shape",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[4],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[21],
            WorkoutExercises.allExercises[14], WorkoutExercises.allExercises[9], WorkoutExercises.allExercises[15], WorkoutExercises.allExercises[10], WorkoutExercises.allExercises[35]),
        40
    )

    // Fast Workout
    val sevenMnClassicWorkout = Workout(
        10,
        "7 min Classic",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[9],
            WorkoutExercises.allExercises[21],
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[14], WorkoutExercises.allExercises[7], WorkoutExercises.allExercises[9], WorkoutExercises.allExercises[21], WorkoutExercises.allExercises[10]),
        40,
    )
    val sevenMnFatBurningWorkout = Workout(
        11,
        "7 min Fat Burning",
        listOf(
            WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[11],
            WorkoutExercises.allExercises[3],
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[9], WorkoutExercises.allExercises[30],
            WorkoutExercises.allExercises[11], WorkoutExercises.allExercises[3], WorkoutExercises.allExercises[31], WorkoutExercises.allExercises[9]),
        55
    )
    val sevenMnAbsWorkout = Workout(
        12,
        "7 min Abs",
        listOf(
            WorkoutExercises.allExercises[31],
            WorkoutExercises.allExercises[1],
            WorkoutExercises.allExercises[4],
            WorkoutExercises.allExercises[2],
            WorkoutExercises.allExercises[31], WorkoutExercises.allExercises[1], WorkoutExercises.allExercises[4], WorkoutExercises.allExercises[2], WorkoutExercises.allExercises[35]),
        50
    )

    // challenge
    val plankChallengeWorkout = Workout(
        13,
        "Plank Challenge",
        listOf(
            WorkoutExercises.allExercises[9],
            WorkoutExercises.allExercises[11],
            WorkoutExercises.allExercises[12],
            WorkoutExercises.allExercises[29],
            WorkoutExercises.allExercises[10],
            WorkoutExercises.allExercises[9],
            WorkoutExercises.allExercises[10], WorkoutExercises.allExercises[11], WorkoutExercises.allExercises[12], WorkoutExercises.allExercises[29], WorkoutExercises.allExercises[10]),
        80,
    )
    val pushUpChallengeWorkout = Workout(
        14,
        "Push-up Challenge",
        listOf(
            WorkoutExercises.allExercises[13],
            WorkoutExercises.allExercises[15],
            WorkoutExercises.allExercises[14],
            WorkoutExercises.allExercises[16],
            WorkoutExercises.allExercises[17],
            WorkoutExercises.allExercises[18],
            WorkoutExercises.allExercises[19],
            WorkoutExercises.allExercises[20], WorkoutExercises.allExercises[21], WorkoutExercises.allExercises[13], WorkoutExercises.allExercises[15], WorkoutExercises.allExercises[14]),
        75
    )
    val crunchesChallengeWorkout = Workout(
        15,
        "Crunch Challenge",
        listOf(
            WorkoutExercises.allExercises[1],
            WorkoutExercises.allExercises[2],
            WorkoutExercises.allExercises[7],
            WorkoutExercises.allExercises[8],
            WorkoutExercises.allExercises[5],
            WorkoutExercises.allExercises[11],
            WorkoutExercises.allExercises[1],
            WorkoutExercises.allExercises[2], WorkoutExercises.allExercises[7], WorkoutExercises.allExercises[8], WorkoutExercises.allExercises[5], WorkoutExercises.allExercises[11]),
        90
    )

    val allWorkouts = listOf(
        absWorkout, chestWorkout, armsWorkout,
        bellyFatBurnerWorkout, rippedVAbsWorkout, getRidManBoobWorkout,
        immunityBoosterWorkout, athletesChoicesWorkout, stayInShapeWorkout,
        sevenMnClassicWorkout, sevenMnFatBurningWorkout, sevenMnAbsWorkout,
        plankChallengeWorkout, pushUpChallengeWorkout, crunchesChallengeWorkout
    )


}
enum class WorkoutName{
    AbsWorkout, ChestWorkout, ArmWorkout,
    BellyFatBurnerWorkout, RippedVAbsWorkout, GetRidManBoobWorkout,
    ImmunityBoosterWorkout, AthletesChoicesWorkout, StayInShapeWorkout,
    SevenMnClassicWorkout, SevenMnFatBurningWorkout, SevenMnAbsWorkout,
    PlankChallengeWorkout, PushUpChallengeWorkout, CrunchesChallengeWorkout
}

enum class WorkoutGroup{
    BodyPartWorkouts, BurnerWorkouts, QuarantineWorkouts, FastWorkouts, Challenges
}