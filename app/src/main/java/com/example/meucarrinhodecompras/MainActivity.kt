package com.example.meucarrinhodecompras

import android.R.attr.category
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.meucarrinhodecompras.components.CategoryCard
import com.example.meucarrinhodecompras.components.ItemPill
import com.example.meucarrinhodecompras.components.ProgressCard
import com.example.meucarrinhodecompras.components.Header
import com.example.meucarrinhodecompras.ui.theme.GreenPrimary

// MainActivity é a primeira tela que o Android chama quando o aplicativo inicia.
// Dentro de setContent dizemos qual interface o app vai mostrar.
// No nosso caso, a interface começa pela função composable MainScreen().
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que o conteúdo da tela ocupe todo o espaço disponível,
        // inclusive atrás da barra de status.
        enableEdgeToEdge()

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // Essa variável guarda quais categorias estão abertas (expandidas).
    // remember faz com que o valor seja mantido enquanto essa tela estiver ativa.
    // mutableStateOf torna essa variável "reativa", ou seja, quando o valor muda, a interface atualiza automaticamente.
    var expandedCategories by remember {
        mutableStateOf(setOf<Category>())
    }

    // Como ainda não implementamos a tela de adicionar itens, utilizamos essa lista com itens "falsos" para
    // pupular a interface.
    var items by remember {
        mutableStateOf(
            listOf(
                Item("Bananas", Category.FRUITS, "6x", checked = true),
                Item("Maçãs", Category.FRUITS, "4x"),
                Item("Alface", Category.FRUITS, "1x"),

                Item("Leite", Category.DAIRY, "2x"),
                Item("Ovos", Category.DAIRY, "12x"),
                Item("Queijo", Category.DAIRY, "1x", checked = true),

                Item("Detergente", Category.CLEANING, "1x"),
                Item("Sabão em pó", Category.CLEANING, "1x"),

                Item("Pão francês", Category.BAKERY, "6x"),
                Item("Croissant", Category.BAKERY, "2x"),

                Item("Carne moída", Category.MEAT, "500g"),
                Item("Frango", Category.MEAT, "1kg"),

                Item("Biscoito", Category.SNACKS, "2x"),
                Item("Chocolate", Category.SNACKS, "1x", checked = true),
                Item("Batata chips", Category.SNACKS, "1x")
            )
        )
    }

    // Essas variáveis controlam a barra de progresso no topo da tela.
    val checked = items.count { it.checked } // Conta quantos itens estão marcados como concluídos
    val total = items.size  // Quantidade total de itens no carrinho (tamanho da lista)
    val progress = if (items.isEmpty()) 0f else checked.toFloat() / items.size    // Calcula o progresso da barra (valor entre 0 e 1).  Se a lista estiver vazia, evitamos dividir por zero.

    // O Scaffold é um componente que organiza a estrutura básica da tela.
    // Ele facilita adicionar elementos comuns como botão flutuante, barra superior, snackbar, etc.
    Scaffold(
        containerColor = Color.White,

        // floatingActionButton adiciona o botão redondo no canto inferior da tela
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* aqui podemos adicionar a intent para navegar até a tela de adicionar item */ },
                containerColor = GreenPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState()) // permite rolagem da tela
        ) {

            Spacer(Modifier.height(18.dp))
            Header(title = "Meu carrinho")
            Spacer(Modifier.height(25.dp))

            ProgressCard(
                progress = progress,
                checked = checked,
                total = total
            )

            Spacer(Modifier.height(25.dp))

            // Category.entries contém todas as categorias definidas no enum Category. Usamos forEach para percorrer cada categoria e criar um card para ela.
            Category.entries.forEach { category ->

                CategoryCard(
                    category = category,
                    // Verifica se essa categoria está no conjunto das categorias abertas
                    expanded = category in expandedCategories,

                    // Quando o usuário toca no card:
                    // se estiver aberto -> fechamos
                    // se estiver fechado -> abrimos
                    onToggle = {
                        expandedCategories =
                            if (category in expandedCategories) {
                                expandedCategories - category
                            } else {
                                expandedCategories + category
                            }
                    }
                ) {

                    // Como um dos parâmetros do CategoryCard é uma função composable, passamos uma coluna de itens usando o componente
                    // ItemPill, atencao para: ainda estamos no forEach, então aqui ele pega apenas a categoria que está no momento atual da iteração
                    // e filtramos apenas os itens que pertencem a essa categoria
                    val categoryItems = items.filter { it.category == category }

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        // Para cada item da categoria, mostramos um ItemPill
                        categoryItems.forEach { item ->
                            ItemPill(
                                item = item,
                                // Quando o usuário marca ou desmarca um item procuramos o item selecionado na lista "items" e modificamos o valor de checked
                                onToggle = {
                                    items = items.map {
                                        if (it == item)
                                            it.copy(checked = !it.checked)
                                        else
                                            it
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}