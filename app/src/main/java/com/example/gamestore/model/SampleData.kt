package com.example.gamestore.model

import com.example.gamestore.R

object SampleData {

    val games: List<Game> = listOf(


        Game(
            id = 1,
            title = "Cyberpunk2077",
            genre = "RPG / Cyberpunk",
            description = "Cyberpunk 2077 é um jogo eletrônico de RPG de ação jogado numa perspectiva em primeira pessoa na pele de V.",
            imageResId = R.drawable.cyberpunk2077,
            items = listOf(

                GameItem(
                    id = 101,
                    name = "Cyberpunk 2077 Phantom Liberty",
                    description = "Expansão de aventura e espionagem para salvar a presidente dos NUSA.",
                    priceUsd = 29.99,
                    imageResId = R.drawable.phantomliberty
                ),

                GameItem(
                    id = 102,
                    name = "Johnny Silverhand Gun",
                    description = "Malorian Arms 3516, pistola icónica utilizada por Johnny Silverhand.",
                    priceUsd = 9.99,
                    imageResId = R.drawable.johnnysilverhand
                ),

                GameItem(
                    id = 103,
                    name = "Skin Lendária do Adam Smasher",
                    description = "Skin baseada no mercenário ciborgue Adam Smasher, chefe de segurança da Arasaka.",
                    priceUsd = 2.49,
                    imageResId = R.drawable.adamsmasher
                )
            )
        ),



        Game(
            id = 2,
            title = "Football Manager 2024",
            genre = "Simulação / Gerenciamento de futebol",
            description = "Simulador desportivo onde assumes o papel de treinador e geres uma carreira de sucesso.",
            imageResId = R.drawable.footballmanager2024,
            items = listOf(

                GameItem(
                    id = 201,
                    name = "Game Editor",
                    description = "Ferramenta que permite editar a base de dados do FM24, adicionar lendas e muito mais.",
                    priceUsd = 8.99,
                    imageResId = R.drawable.editor
                ),

                GameItem(
                    id = 202,
                    name = "Pacote de Faces",
                    description = "Pacote DF11 com milhares de faces atualizadas com design exclusivo.",
                    priceUsd = 5.49,
                    imageResId = R.drawable.d11facepack
                ),

                GameItem(
                    id = 203,
                    name = "Jogadores Lendários",
                    description = "Inclui Pelé, Maradona, Zidane, Cruyff, Ronaldinho e muitos outros.",
                    priceUsd = 10.99,
                    imageResId = R.drawable.legends
                )
            )
        )
    )
}
