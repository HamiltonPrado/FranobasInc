package com.example.gamestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamestore.model.Game
import com.example.gamestore.model.GameItem
import com.example.gamestore.model.SampleData
import com.example.gamestore.ui.theme.GameStoreTheme

class GameDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = intent.getSerializableExtra("game") as? Game

        setContent {
            GameStoreTheme {
                Surface {
                    if (game != null) {
                        GameDetailScreen(
                            game = game,
                            onBack = { finish() }
                        )
                    } else {
                        Text("Erro ao carregar o jogo")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    game: Game,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet && selectedItem != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            containerColor = Color.Transparent,
            shape = RectangleShape,
            dragHandle = {}
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                BottomSheetContent(
                    item = selectedItem!!,
                    imageResId = selectedItem!!.imageResId,
                    onBuyClick = {
                        showSheet = false
                        Toast.makeText(
                            context,
                            "Acabou de comprar o item ${selectedItem!!.name} por \$${"%.2f".format(selectedItem!!.priceUsd)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }
    }

    Scaffold { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8EFFB))
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "←",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.clickable { onBack() }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }


            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    GameImageShape(
                        imageResId = game.imageResId,
                        contentDescription = "Game image",
                        size = 96
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = game.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }


            item {
                Text(
                    text = "Purchasable Items",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }


            items(game.items) { item ->
                PurchasableItemRow(
                    item = item,
                    onClick = {
                        selectedItem = item
                        showSheet = true
                    }
                )
            }
        }
    }
}

@Composable
fun PurchasableItemRow(
    item: GameItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8E3EC)
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GameImageShape(
                imageResId = item.imageResId,
                contentDescription = null,
                size = 64
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "\$${"%.2f".format(item.priceUsd)}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    item: GameItem,
    imageResId: Int,
    onBuyClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDE4FF)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(60.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF7C7386))
            )

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.Top
            ) {
                GameImageShape(
                    imageResId = imageResId,
                    contentDescription = null,
                    size = 80
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\$${"%.2f".format(item.priceUsd)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = onBuyClick,
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Buy with 1-click")
                }
            }
        }
    }
}

@Composable
fun GameImageShape(
    imageResId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Int = 80
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFD4D6DB)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "GameDetailActivity Preview")
@Composable
fun GameDetailScreenPreview() {
    GameStoreTheme {
        val sampleGame = SampleData.games.first()
        GameDetailScreen(
            game = sampleGame,
            onBack = {}
        )
    }
}

@Preview(showBackground = true, name = "BottomSheetContent Preview")
@Composable
fun BottomSheetOnlyPreview() {
    GameStoreTheme {
        val sampleItem = SampleData.games.first().items.first()
        BottomSheetContent(
            item = sampleItem,
            imageResId = sampleItem.imageResId,
            onBuyClick = {}
        )
    }
}
