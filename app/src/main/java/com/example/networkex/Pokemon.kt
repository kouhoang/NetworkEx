package com.example.networkex

data class Pokemon(
    val name: String,
    val url: String,
) {
    val number: Int
        get() =
            url
                .split("/")
                .filter { it.isNotEmpty() }
                .last()
                .toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}
