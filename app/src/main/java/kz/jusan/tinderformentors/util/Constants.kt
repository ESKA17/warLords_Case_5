package kz.jusan.tinderformentors.util

import androidx.annotation.DrawableRes
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.presentation.models.Card
import kz.jusan.tinderformentors.presentation.models.Match
import kz.jusan.tinderformentors.presentation.models.Post

object Constants {

    const val LOCAL_BASE_URL = "http://10.0.2.2:8087/"
    const val DEPLOYED_BASE_URL = "https://avada-codeavra.herokuapp.com/"

    val INITIAL_CARDS_LIST = listOf(
        Card(
            0,
            "Batyrkhan",
            23,
            R.drawable.ic_man_2
        ),
        Card(
            1,
            "Aigerim",
            23,
            R.drawable.ic_woman
        ),
        Card(
            2,
            "Ramazan",
            23,
            R.drawable.ic_man_2
        ),
        Card(
            3,
            "Sabit",
            23,
            R.drawable.ic_man_2
        ),
        Card(
            4,
            "Nauryzbay",
            23,
            R.drawable.ic_man_2
        ),
    )

    private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

    val POSTS_LIST = listOf(
        Post(
            id = 0,
            avatar = R.drawable.ic_profile,
            authorName = "Dana Makarova",
            university = "2022-07-14",
            message = "Someone help me with programming assignment pls pls pls <3"
        ),
        Post(
            id = 1,
            avatar = R.drawable.ic_profile,
            authorName = "Nauryzbay Manap",
            university = "2022-07-14",
            message = "Could you please explain me algorithms?"
        ),
        Post(
            id = 2,
            avatar = R.drawable.ic_profile,
            authorName = "Nauryzbay Manap",
            university = "2022-07-14",
            message = "Продам камри 55 3.5 литра"
        ),
    )

    val MATCHES_LIST = listOf(
        Match(
            id = 5,
            avatar = R.drawable.ic_profile,
            authorName = "Test Account",
            authorEmail = "doom.98.tb@gmail.com",
            university = "Nazarbayev University",
            subjects = "Math, Physics, Informatics"
        ),
        Match(
            id = 0,
            avatar = R.drawable.ic_profile,
            authorName = "Batyrkhan Tokmyrza",
            authorEmail = "btokmyrza@gmail.com",
            university = "Nazarbayev University",
            subjects = "Math, Physics, Informatics"
        ),
        Match(
            id = 1,
            avatar = R.drawable.ic_profile,
            authorName = "Aigerim",
            authorEmail = "doom.98.tb@gmail.com",
            university = "Glasgow University",
            subjects = "Math, Informatics"
        ),
        Match(
            id = 2,
            avatar = R.drawable.ic_profile,
            authorName = "Ramazan",
            authorEmail = "doom.98.tb@gmail.com",
            university = "Nazarbayev University",
            subjects = "Math, Physics, Informatics"
        ),
        Match(
            id = 3,
            avatar = R.drawable.ic_profile,
            authorName = "Nauryzbay",
            authorEmail = "doom.98.tb@gmail.com",
            university = "Nazarbayev University",
            subjects = "Math, Physics"
        ),
        Match(
            id = 4,
            avatar = R.drawable.ic_profile,
            authorName = "Sabit",
            authorEmail = "doom.98.tb@gmail.com",
            university = "Nazarbayev University",
            subjects = "Math, Physics"
        )
    )

    val MAJORS = mapOf(
        0 to "Math",
        1 to "Physics",
        2 to "Chemistry",
        3 to "Biology",
        4 to "Informatics",
        5 to "History"
    )

    val NIS_SCHOOLS = arrayOf(
        "Международная школа г.Нур-Султан",
        "Школа физико-математического направления г.Нур-Султан",
        "Интеллектуальная школа г.Нур-Султан",
        "Школа физико-математического направления г.Кокшетау",
        "Школа физико-математического направления г.Талдыкорган",
        "Школа физико-математического направления г.Семей",
        "Школа физико-математического направления г.Уральск",
        "Школа химико-биологического направления г.Усть-Каменогорск",
        "Школа физико-математического направления г.Актобе",
        "Школа химико-биологического направления г.Караганда",
        "Школа химико-биологического направления г.Шымкент",
        "Школа физико-математического направления г.Шымкент",
        "Школа физико-математического направления г.Тараз",
        "Школа химико-биологического направления г.Кызылорда",
        "Школа химико-биологического направления г.Павлодар",
        "Школа химико-биологического направления г.Атырау",
        "Школа физико-математического направления в г.Алматы",
        "Школа физико-математического направления г.Костанай",
        "Школа химико-биологического направления г.Петропавловск",
        "Школа химико-биологического направления г.Алматы",
        "Школа химико-биологического направления г.Актау",
        "Школа химико-биологического направления г.Туркестан"
    )

}