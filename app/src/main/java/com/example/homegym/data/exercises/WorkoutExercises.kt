package com.example.homegym.data.exercises

import com.example.homegym.R

sealed class WorkoutExercises {
    companion object {
        val allExercises = listOf(
            Exercise(
                0,
                "Sleep",
                "Start by lying down on your bed. Close your eyes and empty your mind. Get comfortable as much as you want. Hold it for as long as you need to get its effect.",
                R.drawable.backworkout,
                ExerciseType.CountDuration,
                ExerciseLevel.Hard,
                BodyPartBestFor.Stretch,
                "Try to rest for a while after a spending a lot of energy in a day. Health is also important <3"
            ),
            Exercise(
                1,
                "Crunches",
                "Start by lying down on your back on a mat or towel. Bend your knees and keep your feet flat on the ground. Cross your arms over your chest. Then, lift your head and shoulders off the ground using your stomach muscles. Hold for a second, then lower yourself back down.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Abs
            ),
            Exercise(
                2,
                "Reverse-crunches",
                "Lie flat on your back with your hands beneath your hips. Bend your knees and lift them towards your head, drawing them upward slightly at the end of the movement. Lower your feet back down just above the floor to complete one repetition.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Abs
            ),
            Exercise(
                3,
                "Flutter-kicks",
                "Start by lying down on your back and placing both your hands underneath your buttocks. Keep your lower back on the ground as you lift the right leg off the ground slightly past hip height, and lift the left leg so it hovers a few inches off the floor. Hold for 2 seconds, then switch the position of the legs, making a flutter kick motion.",
                R.drawable.backworkout,
                ExerciseType.CountAmount,
                ExerciseLevel.Hard,
                BodyPartBestFor.Abs
            ),
            Exercise(
                4,
                "Heel touch",
                "Lie face-up on an exercise mat. Keep your back flat and bend your knees to a 90-degree angle with your feet firmly planted on the floor. Engage your core as you bend your spine to reach your right hand toward your right ankle. Repeat this movement on your left side, alternating sides between each repetition.",
                R.drawable.backworkout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Easy,
                BodyPartBestFor.Abs
            ),
            Exercise(
                5,
                "Knee-to-elbow",
                "Lie on the floor, crossing your right leg across your bent left knee. Clasp your hands behind your head, beginning with your shoulder blades on the ground. This will be your starting position. Perform the motion by flexing the spine and rotating your torso to bring the left elbow to the right knee. Return to the starting position and repeat the movement for the desired number of repetitions before switching sides.",
                R.drawable.workout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Medium,
                BodyPartBestFor.Abs
            ),
            Exercise(
                6,
                "Half-wipers",
                "Lie flat on your back, with your arms straight to your sides near your body. Bring feet, knees, and hips up to 90 degrees. Start dropping both knees to the ground on one side then to other side. Keep your shoulder flat on the floor. Repeat this.",
                R.drawable.backworkout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Easy,
                BodyPartBestFor.Abs
            ),
            Exercise(
                7,
                "Sit-ups",
                "Start by lying on your back with your knees bent and feet flat on the floor. You can place your arms across your chest or behind your head. Engage your core and lift both your upper and lower vertebrae from the floor until everything above your buttocks is not touching the ground. Then, lower yourself back down to the starting position.",
                R.drawable.backworkout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Abs
            ),
            Exercise(
                8,
                "Leg-raises",
                "Lie flat on your back and extend your legs. Extend your arms and place them by the sides of your body, then lightly press your hands into the ground. Exhale and lift your legs upwards until you reach a point where you feel as though your lower back is going to “peel” off the ground. Once you reach this point, stop and begin lowering your legs back down while inhaling. Repeat for the desired number of repetitions.",
                R.drawable.backworkout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Abs
            ),
            Exercise(
                9,
                "Planks",
                "Start by lying face-down on the floor. Place your elbows under your shoulders and near your sides with your palms on the floor. Extend your legs and squeeze your thigh muscles. Engage your abs to keep your upper body stable as you lift it off the floor. Make sure that your back stays in a straight line by keeping your hips square to the ground and your knees straight. Stay like this for the required duration.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Medium,
                BodyPartBestFor.Abs,
                "Breathe slow and steady; don’t hold your breath. Engage your core during the entire movement to prevent your back from getting strained or injured"
            ),
            Exercise(
                10,
                "Side-planks left/right",
                "Lie on your right side with your legs extended and stacked on top of each other. Place your right elbow under your right shoulder with your forearm pointing away from you. Engage your abdominal muscles and lift your hips off the mat so that you’re supporting your on your elbow and the side of your right foot. Your body should be in a straight line from your ankles to your head. Hold this position for the desired duration before switching sides",
                R.drawable.workout,
                ExerciseType.CountDurationTwoSide,
                ExerciseLevel.Hard,
                BodyPartBestFor.Abs
            ),
            Exercise(
                11,
                "Plank-crunches",
                "Start in a plank position on your hands with your toes touching the ground. Drive one knee at a time to the chest or same elbow and then alternate.",
                R.drawable.workout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Medium,
                BodyPartBestFor.Abs
            ),
            Exercise(
                12,
                "Climber-taps",
                "Get in the straight arm plank position on a mat. Now tuck your left knee as you simultaneously lift the right arm to tap the left foot. Return the leg to the starting position as you simultaneously lower your hand. Alternate the hand and foot in the next rep.",
                R.drawable.workout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Hard,
                BodyPartBestFor.Abs
            ),
            Exercise(
                13,
                "Knee push-ups",
                "place your hands shoulder-width apart under your shoulders and rest your lower body on your knees. Engage your glutes and your core as you bend your elbows and lower your body closer to the floor",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Chest
            ),
            Exercise(
                14,
                "Push-ups",
                "Get on the floor on all fours and position your hands slightly wider than your shoulders. Extend your legs back so you are balanced on your hands and toes. Contract your abs and tighten your core. Slowly bend your elbows and lower yourself to the floor until your elbows are at a 90-degree angle. Then push back up through your hands to return to the start position.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Chest,
                "Maintain good form, including hip alignment and hand and feet position. Stop if you experience pain."
            ),
            Exercise(
                15,
                "Wide-arm push-ups",
                "Start in plank position with your hands wider than your shoulders. Face your fingers forward or slightly to the outside. Slowly bend your elbows out to the side as you lower your body toward the floor. Pause when your chest is just below your elbows. Engage your core as you press into your hands to lift your body back to the starting position",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Chest
            ),
            Exercise(
                16,
                "Military push-ups",
                "Start in a high plank position with your hands under your shoulders and your feet together or slightly apart. Keep your body in a straight line and lower yourself until your chest is close to the ground and your upper arms are parallel to it. Then push yourself back up to the starting position without locking your arms.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Hard,
                BodyPartBestFor.Chest
            ),
            Exercise(
                17,
                "Diamond push-ups",
                "Move into push-up position. Place your hands under your chest and make a diamond shape. Lower yourself to the ground, then push up.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Chest
            ),
            Exercise(
                18,
                "Staggered push-ups",
                "Get into push-up position. Your hands should be outside of shoulder width but staggered, with one being higher than the other. Initiate the movement by flexing the elbows and lowering your torso to the ground. Do not allow your hips to rise or to sag. Pause at the bottom of the motion and then extend at the elbows to return to the starting position.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Hard,
                BodyPartBestFor.Chest
            ),
            Exercise(
                19,
                "Incline push-ups",
                "similar to classic push-up exercise where you push yourself off from a raised object instead of the ground.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Chest
            ),
            Exercise(
                20,
                "Decline push-ups",
                "Kneel down with your back to the bench. Put your hands on the floor, shoulders over your wrists and elbows at 45 degrees. Place your feet on top of the bench. Brace your core, glutes, and quads. Bend your elbows and lower your chest to the floor, keeping your back and neck straight. Push into the floor to return to starting position.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Hard,
                BodyPartBestFor.Chest
            ),
            Exercise(
                21,
                "Push-ups with rotations",
                "Begin in a push-up position. Maintain alignment through the head, spine and hips. With a flat back, slowly lower the body toward the floor, lowering and squeezing the shoulder blades. Push up to the starting position, then lift one arm up toward ceiling while rotating body. Repeat for the desired number of repetitions.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Chest
            ),
            Exercise(
                22,
                "Arm-raises",
                "Raise both of your arms straight up above your head and wait for 2 sec to drop it slowly.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                23,
                "Side-arm-raises",
                "Raise both of your arms side way widely and wait for 2 sec to drop it slowly.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                24,
                "Punches",
                "Just punch",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                25,
                "Triceps-dips",
                "Sit on the edge of a sturdy chair or bench. Bring your arms down along your sides and rest your palms on your chair, wrapping your fingers under the front of the seat. Straighten your legs out in front of you and place your heels firmly on the ground. Lower yourself by bending your elbows back until they reach a 90-degree angle. Straighten your elbows fully to lift yourself back up.",
                R.drawable.workout,
                ExerciseType.CountAmount,
                ExerciseLevel.Medium,
                BodyPartBestFor.Arm
            ),
            Exercise(
                26,
                "Arm-scissors",
                "Spread your arms widely. Then move both of your arms to the opposite direction back and forth.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                27,
                "Arm circle clockwise",
                "Spread your arms widely and start circling them in a small circle clockwise.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                28,
                "Arm circle counterclockwise",
                "Spread your arms widely and start circling them in a small circle counterclockwise.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Arm
            ),
            Exercise(
                29,
                "Shoulder-taps",
                "Start in a straight arm plank position with your body forming one straight line for the floor through the crown of your head. Now, with control, while keeping your hips and shoulders level and squared in front of you, lift one palm to the top of your opposite arm and pause for one second before returning to start. Repeat on the other side.",
                R.drawable.workout,
                ExerciseType.CountAmountTwoSide,
                ExerciseLevel.Hard,
                BodyPartBestFor.Arm,
                "Keep your hips steady and your core engaged"
            ),
            Exercise(
                30,
                "Jumping jacks",
                "Start by standing tall with your hands at your sides. Jump vertically, extending your feet out slightly more than shoulder-width apart, and lift your arms laterally above your head. After jumping in the air, gently land in the first position with arms at your sides and feet shoulder width apart. Repeat as needed.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Warmup,
                "Always warm up your body properly before exercising. This exercise is the best choice."
            ),
            Exercise(
                31,
                "Mountain climber",
                "Start by getting into a straight arm plank position. Then, pull one knee up and in toward your midsection. Relax your midsection and push your knee back toward your other foot slowly. Straighten your leg and set your foot back on the ground behind you. Continue alternating the movement with both knees.",
                R.drawable.workout,
                ExerciseType.CountDuration,
                ExerciseLevel.Medium,
                BodyPartBestFor.Warmup
            ),
            Exercise(
                32,
                "Shoulder stretch",
                "Stand with your feet hip-width apart. Stretch your right arm out straight. Bring your right arm across your body so that your hand points to the floor on the other side of your left leg.",
                R.drawable.stretch,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Stretch
            ),
            Exercise(
                33,
                "Triceps stretch",
                "Extend your right arm to the ceiling, then bend at the elbow to bring the right palm toward the center of your back, resting your middle finger along your spine. Use your left hand to gently push your elbow in toward the center and down. Hold this for a few seconds then change arm.",
                R.drawable.stretch,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Stretch
            ),
            Exercise(
                34,
                "Child's pose",
                "Come to your hands and knees on the ground. Keeping the tops of your feet on the floor with the big toes touching. Rest your belly between your thighs and root your forehead to the floor. Stretch your arms in front of you with the palms toward the floor.",
                R.drawable.stretch,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Stretch
            ),
            Exercise(
                35,
                "Cobra stretch",
                "Lie flat on your stomach with your hands under your shoulders and your elbows close to your sides. Slowly raise your head and chest off the ground, keeping your elbows close to your sides and pressing down into your hands. Keep your hips on the ground and hold the stretch.",
                R.drawable.stretch,
                ExerciseType.CountDuration,
                ExerciseLevel.Easy,
                BodyPartBestFor.Stretch,
                "Release tight muscles: Instead of squeezing your buttocks, which can compress the lower back, relax them"
            )
        )
    }
}

