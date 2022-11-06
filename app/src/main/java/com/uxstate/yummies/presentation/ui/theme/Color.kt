package com.uxstate.yummies.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// primary
val Yellow200A = Color(0xFFFFFF00)

// primary variant
val Yellow100A = Color(0xFFFFFF5A)

// variants
val Yellow400 = Color(0xFFFFEE58)
val Yellow300 = Color(0xFFFFF176)
val Yellow200 = Color(0xFFFFF59D)
val Yellow50 = Color(0xFFFFFDE7)

// primary dark
val Lime800 = Color(0xFFC7CC00)

// variants
val Lime300 = Color(0xFFDCE775)
val Lime200 = Color(0xFFE6EE9C)
val Lime50 = Color(0xFFF9FBE7)

val Gray100 = Color(0x7F000000)

val Colors.statusBarColor
    get() = if (this.isLight) Lime800 else Color.Black

val Colors.gradientColors
    get() = if (this.isLight) listOf(Yellow300, Yellow200, Yellow50)
    else listOf(Lime300, Lime200, Lime50)

val Colors.starredStarColor
    get() = if (this.isLight) Color(0xFFF26227)
    else Color(0xFFEE8A62)

val Colors.cardColor
    get() = if (this.isLight)Yellow300 else Lime300