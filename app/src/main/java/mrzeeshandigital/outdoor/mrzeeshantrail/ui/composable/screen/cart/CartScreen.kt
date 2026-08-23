package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.CartItemUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val cartItems = (state as? DataUiState.Populated)?.data.orEmpty()
    if (cartItems.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Default.ShoppingBag, null, modifier = Modifier.size(72.dp), tint = MaterialTheme.colorScheme.primary)
            Text("Your trail bag is empty", style = MaterialTheme.typography.headlineMedium)
            Text("Start shopping to prepare your next adventure.")
        }
    } else {
        Column(modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(cartItems, key = { it.productId }) { item ->
                    CartItem(
                        item = item,
                        onPlus = { viewModel.incrementProductInCart(item.productId) },
                        onMinus = {
                            if (item.quantity == 1) {
                                viewModel.deleteFromCart(item.productId)
                            } else {
                                viewModel.decrementItemInCart(item.productId)
                            }
                        },
                        onDelete = { viewModel.deleteFromCart(item.productId) },
                    )
                }
            }
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Subtotal")
                    Text("£%.2f".format(total))
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total", style = MaterialTheme.typography.headlineMedium)
                    Text("£%.2f".format(total), style = MaterialTheme.typography.headlineMedium)
                }
                Button(
                    onClick = onNavigateToCheckoutScreen,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text("Proceed to Checkout")
                }
            }
        }
    }
}

@Composable
private fun CartItem(
    item: CartItemUiState,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    onDelete: () -> Unit,
) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Row(Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.productImageUrl,
                contentDescription = item.productTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(72.dp),
            )
            Column(Modifier.weight(1f).padding(horizontal = 10.dp)) {
                Text(item.productTitle, style = MaterialTheme.typography.titleLarge)
                Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onMinus) {
                        Icon(Icons.Default.Remove, "Decrease quantity")
                    }
                    Text(item.quantity.toString())
                    IconButton(onClick = onPlus) {
                        Icon(Icons.Default.Add, "Increase quantity")
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Remove item")
            }
        }
    }
}
