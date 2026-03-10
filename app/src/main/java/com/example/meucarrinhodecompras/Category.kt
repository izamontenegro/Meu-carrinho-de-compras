package com.example.meucarrinhodecompras

import com.example.meucarrinhodecompras.ui.theme.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color

// Enum é um tipo especial de classe usado quando temos um conjunto FIXO de opções. Diferente de uma classe normal, onde podemos criar quantos objetos quisermos,
// em um enum nós definimos todas as opções aqui dentro. Ou seja: não criamos novas categorias em qualquer lugar do código.
// Em vez disso, usamos apenas as que já existem aqui.
//
// Isso ajuda a organizar o código e evita erros (como digitar categorias diferentes
// por engano, já que só podemos usar as que estão definidas no enum).
//
// Outra vantagem é que cada categoria pode ter propriedades próprias,
// como título, cores e até um ícone, como estamos fazendo aqui.
enum class Category(
    val title: String,
    val mainColor: Color,
    val backgroundColor: Color,
    val itemBorderColor: Color,
    val iconRes: Int
) {

    FRUITS(
        title = "Frutas e Vegetais",
        mainColor = GreenPrimary,
        backgroundColor = GreenHighlight,
        itemBorderColor = GreenSecondary,
        iconRes = R.drawable.lucide_leaf
    ),

    DAIRY(
        title = "Laticínios e Ovos",
        mainColor = OrangePrimary,
        backgroundColor = OrangeHighlight,
        itemBorderColor = OrangeSecondary,
        iconRes = R.drawable.lucide_egg
    ),

    CLEANING(
        title = "Itens de Limpeza",
        mainColor = BluePrimary,
        backgroundColor = BlueHighlight,
        itemBorderColor = BlueSecondary,
        iconRes = R.drawable.lucide_bubbles
    ),

    BAKERY(
        title = "Padaria",
        mainColor = BrownPrimary,
        backgroundColor = BrownHighLight,
        itemBorderColor = BrownSecondary,
        iconRes = R.drawable.lucide_croissant
    ),

    MEAT(
        title = "Carnes",
        mainColor = RedPrimary,
        backgroundColor = RedHighlight,
        itemBorderColor = RedSecondary,
        iconRes = R.drawable.lucide_beef
    ),

    SNACKS(
        title = "Lanches",
        mainColor = PurplePrimary,
        backgroundColor = PurpleHighlight,
        itemBorderColor = PurplenSecondary,
        iconRes = R.drawable.lucide_donut
    )
}