package com.example.meucarrinhodecompras.components

import android.R.attr.category
import android.R.attr.fontWeight
import com.example.meucarrinhodecompras.ui.theme.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.meucarrinhodecompras.*

// CategoryCard é o card de cada categoria da lista.
// Exemplo: Frutas, Padaria, Laticínios...
//
// Ele foi feito como um componente reutilizável.
// Isso significa que, em vez de criar um card diferente para cada categoria,
// criamos um modelo só, e mudamos apenas os dados recebidos.
//
// Ele recebe:
// - category: a categoria que será mostrada no card
// - expanded: informa se o card está aberto ou fechado
// - onToggle: função chamada quando o usuário toca no card
// - modifier: permite ajustar tamanho, espaçamento etc. ao usar esse componente
// - content: o conteúdo que aparecerá quando o card estiver expandido (a lista de itens usando o 'ItemPill')
@Composable
fun CategoryCard(
    category: Category,
    expanded: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(25.dp)

    // Surface funciona como a "caixa principal" do card.
    // Aqui definimos aparência e comportamento do componente.
    Surface(
        shape = shape,
        color = category.backgroundColor,
        modifier = modifier
            .fillMaxWidth()

            // Adiciona uma borda usando a cor principal da categoria
            .border(1.dp, category.mainColor, shape)

            // Garante que o conteúdo respeite as bordas arredondadas, dando um 'corte' no formato de 'shape'
            .clip(shape)

            // Faz o card inteiro responder ao toque
            .clickable { onToggle() }

            // animateContentSize anima automaticamente mudanças de tamanho.
            // Ou seja: quando o conteúdo interno aparece ou some, o card não "salta" de tamanho de uma vez. Em vez disso, ele cresce ou encolhe de forma suave.
            .animateContentSize()
    ) {
        Column(Modifier.padding(20.dp)) {
            // Esse Row é o cabeçalho do card. Ele organiza os elementos em linha:
            // ícone, nome da categoria e seta.
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(category.mainColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(category.iconRes),
                        contentDescription = category.title,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(Modifier.width(10.dp))

                Text(
                    text = category.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                // Esse Spacer ocupa o espaço livre da linha e empurra a seta para o canto direito do card.

                Spacer(modifier = Modifier.weight(1f))

                // A seta muda dependendo do estado do card:
                // se expanded for true, mostramos seta para cima, se expanded for false, mostramos seta para baixo
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            // AnimatedVisibility controla se o conteúdo extra aparece ou não.
            //
            // Quando expanded = true:
            // o conteúdo fica visível
            //
            // Quando expanded = false:
            // o conteúdo desaparece
            //
            // O mais legal é que isso não acontece de forma "brusca", o Compose aplica uma animação de entrada e saída automaticamente.
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(15.dp))
                    content()
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CategoryCardPreview() {
    Surface(color = Color.White) {
        var expanded by remember { mutableStateOf(true) }

        CategoryCard(
            category = Category.FRUITS,
            expanded = expanded,
            onToggle = { expanded = !expanded },
            modifier = Modifier.padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ItemPill(
                    item = Item(
                        name = "Bananas",
                        category = Category.FRUITS,
                        quantityText = "6x",
                        checked = true
                    ),
                    onToggle = {},
                    modifier = Modifier.fillMaxWidth()
                );
                ItemPill(
                    item = Item(
                        name = "Maças",
                        category = Category.FRUITS,
                        quantityText = "5x",
                        checked = true
                    ),
                    onToggle = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// Repare que CategoryCard não decide sozinho se está aberto ou fechado.
// Ele apenas RECEBE essa informação pelo parâmetro expanded.
//
// Da mesma forma, ele também não muda esse estado sozinho.
// Quando o usuário toca no card, ele apenas chama onToggle().
//
// Quem realmente controla se o card abre ou fecha é a tela que está usando esse componente.