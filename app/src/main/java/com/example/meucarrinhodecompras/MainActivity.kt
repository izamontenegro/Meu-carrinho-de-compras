package com.example.meucarrinhodecompras


import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.meucarrinhodecompras.components.CategoryCard
import com.example.meucarrinhodecompras.components.ItemPill
import com.example.meucarrinhodecompras.components.ProgressCard
import com.example.meucarrinhodecompras.components.Header
import com.example.meucarrinhodecompras.ui.theme.GreenPrimary
import com.example.meucarrinhodecompras.viewModel.ShoppingViewModel
import kotlin.jvm.java

// MainActivity é a primeira tela que o Android chama quando o aplicativo inicia.
// Dentro de setContent dizemos qual interface o app vai mostrar.
// No nosso caso, a interface começa pela função composable MainScreen().
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que o conteúdo da tela ocupe todo o espaço disponível,
        // inclusive atrás da barra de status.
        enableEdgeToEdge()

        val viewModel: ShoppingViewModel by viewModels()

        setContent {

            val items by viewModel.items.collectAsStateWithLifecycle()



//            MainScreen(
////                items = items,
////
////                // Como a lista agora pertence à MainActivity, a MainScreen não altera mais os itens diretamente.
////                // Em vez disso, ela pede a alteração por meio dessa função.
////                onItemsChange = { updatedItems ->
////                    items = updatedItems
////                },
////
////                // Quando o usuário clica no botão de adicionar, criamos uma Intent para abrir a AddItemActivity.
////                // Em vez de usar startActivity direto, usamos o launcher para poder receber o item de volta.
////                onAddClick = {
////                    val intent = Intent(this, AddItemActivity::class.java)
////                    addItemLauncher.launch(intent)
////                }
//            )
        }
    }
}

@Composable
fun MainScreen(
    items: List<Item>,
    toggleItem: (Item) -> Unit,
    onAddClick: () -> Unit
) {
    // Essa variável guarda quais categorias estão abertas (expandidas).
    // remember faz com que o valor seja mantido enquanto essa tela estiver ativa.
    // mutableStateOf torna essa variável "reativa", ou seja, quando o valor muda, a interface atualiza automaticamente.
    var expandedCategories by remember {
        mutableStateOf(setOf<Category>())
    }

    // Essas variáveis controlam a barra de progresso no topo da tela.
    val checked = items.count { it.checked } // Conta quantos itens estão marcados como concluídos
    val total = items.size // Quantidade total de itens no carrinho (tamanho da lista)
    val progress = if (items.isEmpty()) 0f else checked.toFloat() / items.size  // Calcula o progresso da barra (valor entre 0 e 1). Se a lista estiver vazia, evitamos dividir por zero.

    // O Scaffold é um componente que organiza a estrutura básica da tela.
    // Ele facilita adicionar elementos comuns como botão flutuante, barra superior, snackbar, etc.
    Scaffold(
        containerColor = Color.White,

        // floatingActionButton adiciona o botão redondo no canto inferior da tela.
        // Ao clicar nele, chamamos a função recebida por parâmetro para abrir a tela de adicionar item.
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = GreenPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adicionar"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            // Category.entries contém todas as categorias definidas no enum Category.
            // Usamos forEach para percorrer cada categoria e criar um card para ela.
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

                    // Como um dos parâmetros do CategoryCard é uma função composable,
                    // passamos uma coluna de itens usando o componente ItemPill.
                    // Atenção: ainda estamos no forEach, então aqui ele pega apenas a categoria
                    // que está no momento atual da iteração e filtramos apenas os itens que pertencem a essa categoria.
                    val categoryItems = items.filter { it.category == category }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Para cada item da categoria, mostramos um ItemPill
                        categoryItems.forEach { item ->
                            ItemPill(
                                item = item,

                                // Quando o usuário marca ou desmarca um item,
                                // não alteramos mais a lista localmente aqui dentro.
                                // Em vez disso, geramos uma nova lista atualizada e enviamos para a MainActivity
                                // por meio da função onItemsChange.
                                onToggle = { toggleItem(item) }
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