package com.example.meucarrinhodecompras.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meucarrinhodecompras.R

// Componente para o topo da tela
@Composable
fun Header(
    title: String,
    modifier: Modifier = Modifier
){
 Row(
     modifier = modifier.fillMaxSize(),
     horizontalArrangement = Arrangement.Center,
     verticalAlignment = Alignment.CenterVertically
 ){

     // Icon é um componente do Compose usado para mostrar ícones na tela. Aqui estamos usando um ícone
     // que está dentro da pasta "res/drawable" do projeto.
     //
     // painterResource() carrega esse arquivo de imagem para que o Compose consiga desenhá-lo na interface.
    Icon(
        painter = painterResource(R.drawable.utensils_crossed),
        // contentDescription é usado principalmente para acessibilidade (leitores de tela para pessoas com deficiência visual).
        // Como o ícone aqui é apenas decorativo, podemos usar null.
        contentDescription = null,

        // tint permite alterar a cor do ícone
        tint = Color.Black,

        // define o tamanho do ícone
        modifier = Modifier.size(24.dp)
    )

     Spacer(Modifier.width(15.dp))

     Text(
         text = title,
         fontSize = 20.sp,
         fontWeight = FontWeight.SemiBold,
         color = Color(0xFF111111)
     )


 }
}

@Preview
@Composable
private fun HeaderPreview() {
        Surface(color = Color.White) {
            Header(title = "Meu carrinho")
        }
}