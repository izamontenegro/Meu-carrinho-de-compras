package com.example.meucarrinhodecompras

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meucarrinhodecompras.ui.theme.GraySecondary
import com.example.meucarrinhodecompras.ui.theme.GreenPrimary
import com.example.meucarrinhodecompras.ui.theme.GreenSecondary
import com.example.meucarrinhodecompras.ui.theme.LightBlueHighlight
import com.example.meucarrinhodecompras.ui.theme.LightBluePrimary
import com.example.meucarrinhodecompras.ui.theme.LightBlueSecondary


@Composable
fun AddItemScreen(
    onBackClick: () -> Unit,
    onConfirmClick: (Item) -> Unit
) {
    var itemName by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf(Category.FRUITS) }
    var quantity by rememberSaveable { mutableIntStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleActionButton(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.chevron_left),
                            contentDescription = "Voltar",
                            tint = GraySecondary
                        )
                    },
                    borderColor = GraySecondary,
                    backgroundColor = Color.White,
                    onClick = onBackClick
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Novo Item",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                CircleActionButton(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.check),
                            contentDescription = "Confirmar",
                            tint = GreenPrimary
                        )
                    },
                    borderColor = GreenPrimary,
                    backgroundColor = GreenSecondary,
                    onClick = {
                        val newItem = Item(
                            name = itemName.ifBlank { "Novo item" },
                            category = selectedCategory,
                            quantityText = "${quantity}x"
                        )
                        onConfirmClick(newItem)
                    }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Nome do Item",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp)
                    .border(
                        1.dp,
                        LightBluePrimary,
                        RoundedCornerShape(14.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(14.dp)
                    )
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (itemName.isBlank()) {
                    Text(
                        text = "Suco de Laranja...",
                        color = GraySecondary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                BasicTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Selecione a Categoria",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Category.entries.forEach { category ->
                    AddCategoryItem(
                        category = category,
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Qual a quantidade?",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .border(
                        1.dp,
                        LightBlueSecondary,
                        RoundedCornerShape(10.dp)
                    )
                    .background(
                        LightBlueHighlight,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 14.dp)
            ) {
                CircleActionButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.minus),
                            contentDescription = "Diminuir",
                            tint = LightBluePrimary,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    borderColor = LightBluePrimary,
                    backgroundColor = Color.White,
                    onClick = {
                        if (quantity > 1) quantity--
                    }
                )

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = quantity.toString(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Text(
                        text = "UND.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GraySecondary
                    )
                }

                CircleActionButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.plus),
                            contentDescription = "Aumentar",
                            tint = LightBluePrimary,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    borderColor = LightBluePrimary,
                    backgroundColor = Color.White,
                    onClick = { quantity++ }
                )
            }
        }
    }
}

@Composable
private fun AddCategoryItem(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .border(
                    width = 3.dp,
                    color = if (selected) category.mainColor else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        color = category.backgroundColor,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(category.iconRes),
                    contentDescription = category.title,
                    tint = category.mainColor,
                    modifier = Modifier.size(34.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = shortCategoryTitle(category),
            color = if (selected) category.mainColor else Color.Black,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun CircleActionButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    borderColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(backgroundColor, CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

private fun shortCategoryTitle(category: Category): String {
    return when (category) {
        Category.FRUITS -> "Hortaliças"
        Category.DAIRY -> "Laticínios"
        Category.CLEANING -> "Limpeza"
        Category.BAKERY -> "Padaria"
        Category.MEAT -> "Carnes"
        Category.SNACKS -> "Lanches"
    }
}

@Preview
@Composable
fun AddItemPreview() {
    AddItemScreen(onBackClick = {
        print("olá")
    }) { }
}