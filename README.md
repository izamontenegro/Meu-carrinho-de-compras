# 🛒 Meu Carrinho de Compras #

Aplicativo simples de lista de compras desenvolvido em **Kotlin + Jetpack Compose** com foco didático.
Este projeto foi criado para ajudar alunos iniciantes a entender conceitos fundamentais de UI declarativa, componentização e gerenciamento de estado no Compose.

O app permite:
	•	visualizar categorias de produtos
	•	expandir e recolher categorias
	•	marcar itens como concluídos
	•	acompanhar o progresso da lista

Link para o protótipo: https://www.figma.com/design/IzRMmo4UNeJA6GerPOnxlI/App-modelo---lista-de-mercado?node-id=0-1&t=MFm6qeoBcVEKWEOn-1

## 🧠 Conceitos trabalhados neste projeto

Este projeto foi pensado para praticar conceitos importantes do Compose, como:

**Componentização**

A interface foi dividida em componentes reutilizáveis usando funções @Composable:
- Header
- ProgressCard
- CategoryCard
- ItemPill
- CircleCheck
- CountPill

Os componentes foram aplicados na _MainScreen / MainActivity_

⸻

**Estado (State)**

O Compose atualiza automaticamente a interface quando o estado muda. Neste projeto usamos:

- remember
- mutableStateOf

Exemplo:

```
var expandedCategories by remember {
    mutableStateOf(setOf<Category>())
}
```

Sempre que expandedCategories muda, o Compose recompõe a interface.

📚 Documentação para estudo:
https://developer.android.com/jetpack/compose/state

⸻

**Layouts utilizados**
Column: Organiza elementos verticalmente.

Row: Organiza elementos horizontalmente.

Box: Usado como container simples, útil para sobrepor elementos.

📚 Documentação para estudos:
https://developer.android.com/develop/ui/compose/layouts/basics?hl=pt-br

⸻

**Surface**

Surface funciona como um container visual.

Permite definir:
- cor de fundo
- formato
- elevação
- bordas

📚 Documentação para estudos:
https://developer.android.com/codelabs/jetpack-compose-layouts?hl=pt-br#5
⸻

**Modifier**

Modifier é usado para modificar a aparência ou comportamento de um componente.

Exemplos usados no projeto:

```
padding()
border()
clip()
clickable()
fillMaxWidth()
size()
```

📚 Documentação para estudos:
https://developer.android.com/jetpack/compose/modifiers

⸻

**Data Class**
Itens da lista são representados por uma data class.

```
data class Item(
    val name: String,
    val category: Category,
    val quantityText: String,
    val checked: Boolean
)
```

Data classes são ideais para representar dados.

📚 Documentação para estudos:
https://kotlinlang.org/docs/data-classes.html

⸻

**Enum**

Categorias são definidas usando enum class.

```
enum class Category { ... }
```

Enums representam um conjunto **fixo** de opções.

📚
https://kotlinlang.org/docs/enum-classes.html

⸻

## Como rodar o projeto

1️⃣ Clone o repositório

git clone LINK_DO_REPO

2️⃣ Abra no Android Studio

3️⃣ Execute o projeto em um emulador ou dispositivo físico

⸻

📚 Material de estudo recomendado

Compose Basics
https://developer.android.com/jetpack/compose/tutorial

Kotlin Basics
https://kotlinlang.org/docs/home.html
