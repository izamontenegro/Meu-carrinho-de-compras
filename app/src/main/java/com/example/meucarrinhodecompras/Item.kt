package com.example.meucarrinhodecompras

// Classe normal: usamos quando queremos representar um objeto com comportamentos,
// regras e funções mais próprias.
//
// Data class: usamos quando o objetivo principal é representar dados.
//
// Como Item é basicamente um pacote de informações de um produto do carrinho,
// data class faz mais sentido aqui.
data class Item(
    val name: String,
    val category: Category,
    val quantityText: String = "1x",
    val checked: Boolean = false
)

// Usamos data class quando a classe serve principalmente para guardar dados.
// Nesse caso, Item representa apenas as informações de um item do carrinho:
// nome, categoria, quantidade e se ele foi marcado ou não.
//
// A diferença para uma classe comum é que a data class já nos entrega várias
// facilidades prontas automaticamente, sem precisarmos escrever tudo na mão.
// Por exemplo:

// - copy():
// Método que permite criar uma cópia do objeto mudando só um campo
//
// No Compose trabalhamos muito com estados imutáveis, em vez de alterar um objeto existente,
// normalmente criamos uma nova versão dele com uma pequena mudança.
//
// Exemplo:
// item.copy(checked = true) (usamos na MainScreen)
// Ou seja: pegamos o mesmo item e copiamos para gerar outro igual,
// mas com o checked alterado.
//
// Se fosse uma classe comum, teríamos que implementar esse comportamento manualmente
// ou criar outro objeto passando tudo de novo.
