package com.example.meucarrinhodecompras.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import com.example.meucarrinhodecompras.ui.theme.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meucarrinhodecompras.Item

// ItemPill representa um item individual dentro de uma categoria da lista.
//
// A ideia aqui é criar um componente reutilizável: sempre que quisermos
// mostrar um item do carrinho, usamos esse composable.
//
// Ele recebe:
// - item → os dados do item (nome, quantidade, categoria, se está marcado)
// - onToggle → função que será executada quando o usuário clicar no item
// - modifier → permite ajustar o layout quando usamos o componente em outros lugares
//
@Composable
fun ItemPill(
    item: Item,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {

    // RoundedCornerShape(100.dp) deixa o componente bem arredondado,
    // criando o formato de "pílula", que é de onde vem o nome ItemPill.
    val shape = RoundedCornerShape(100.dp)

    // Surface funciona como o "container" visual do componente.
    // Aqui definimos fundo, borda, formato e comportamento de clique.
    Surface(
        shape = shape,
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, item.category.itemBorderColor, shape)
            .clickable { onToggle() }
    ) {
        // Row vai organizar os elementos horizontalmente:
        // primeiro o círculo de check, depois o texto do item.
        Row(
            Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleCheck(
                checked = item.checked,
                onToggle = onToggle,
                main = item.category.mainColor
            )

            Spacer(Modifier.width(10.dp))

            // Se o item estiver marcado, mudamos a aparência do texto
            // para indicar que ele já foi "concluído".
            // O texto fica cinza e com uma linha cortando (strike-through).
            val textColor = if (item.checked) Color(0xFFA9A9A9) else Color(0xFF4E4E4E)
            val deco = if (item.checked) TextDecoration.LineThrough else TextDecoration.None

            Text(
                text = "${item.quantityText} ${item.name}",
                color = textColor,
                textDecoration = deco,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// CircleCheck é o pequeno círculo de marcação que aparece no início do item. Ele funciona como um botão de marcar/desmarcar.
// Ele poderia estar em um arquivo diferente mas coloquei aqui para que vocês possam ver como dois componentes podem estar num mesmo arquivo
@Composable
fun CircleCheck(
    checked: Boolean,
    onToggle: () -> Unit,
    main: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(22.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (checked) main else main,
                shape = CircleShape
            )
            .background(if (checked) main else Color.Transparent)
            // Ao clicar no CircleCheck vai chamar e executar a função passada (onToggle())
            .clickable { onToggle() },
        contentAlignment = Alignment.Center
    ) {
        // Se o item estiver marcado, mostramos o ícone de check dentro do círculo.
        // Caso contrário, o círculo fica vazio.
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

// Detalhe: esse componente (assim como outros no nosso código) não altera o estado diretamente.
// Ele apenas chama a função onToggle() quando o usuário clica.
//
// Quem realmente muda o estado é a tela "pai". Que vai mandar essa função onToggle de lááá da MainScreen.
// Esse padrão é muito comum no Compose e ajuda a manter o código organizado.
