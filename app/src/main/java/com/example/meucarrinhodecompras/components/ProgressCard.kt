package com.example.meucarrinhodecompras.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import com.example.meucarrinhodecompras.ui.theme.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ProgressCard é o card que mostra o andamento da lista de compras.
//
// Ele recebe 3 informações prontas:
// - progress: valor da barra de progresso (de 0f até 1f)
// - checked: quantos itens já foram marcados
// - total: quantidade total de itens
//
// Ou seja, esse componente não calcula nada sozinho. Ele apenas recebe os dados e mostra isso visualmente na tela.
@Composable
fun ProgressCard(
    progress: Float,
    checked: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    // Define o arredondamento das bordas do card
    val shape = RoundedCornerShape(25.dp)

    Surface(
        shape = shape,
        color = BrownHighLight,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BrownHighLight, shape)
    ) {
        // Column organiza os elementos um embaixo do outro:
        // título, subtítulo, contador e barra de progresso.
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // weight(1f) faz essa coluna ocupar todo o espaço disponível, empurrando o contador para a direita.
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Progresso do Carrinho",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrownPrimary
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Quase lá ein!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                CountPill(text = "$checked/$total")
            }

            Spacer(Modifier.height(10.dp))

            // A barra visualiza o progresso da lista. O valor esperado vai de 0f a 1f:
            // 0f = 0%
            // 1f = 100%
            // coerceIn garante que, mesmo se algum valor vier errado, ele será limitado entre 0 e 1.

            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(999.dp)),
                color = Color(0xFF20C05A),
                trackColor = Color(0xFFF1E7BF)
            )
        }
    }
}

// CountPill é a pequena "pílula" que mostra a contagem de itens. Exemplo: 3/10
//
// Criamos esse componente separado porque ele pode ser reutilizado e deixa o código do ProgressCard mais organizado.
@Composable
fun CountPill(
    text: String,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(999.dp)

    Surface(
        shape = shape,
        color = Color.White,
        modifier = modifier.border(1.dp, Color(0xFFEADFAE), shape)
            .border(1.dp, BrownSecondary, shape)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BrownPrimary
        )
    }
}

// Preview serve para visualizar esse componente no editor
// sem precisar rodar o aplicativo inteiro.
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ProgressCardPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            ProgressCard(
                progress = 0.65f,
                checked = 13,
                total = 20,
                modifier = Modifier.padding(18.dp)
            )
        }
    }
}