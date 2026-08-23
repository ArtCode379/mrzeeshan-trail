package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import mrzeeshandigital.outdoor.mrzeeshantrail.R
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    val product = (state as? DataUiState.Populated)?.data
    Box(modifier.fillMaxSize()) {
        if (product != null) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 86.dp),
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)),
                )
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(product.title, style = MaterialTheme.typography.headlineMedium)
                    Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.headlineMedium)
                    Text(
                        stringResource(product.category.titleRes),
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), RoundedCornerShape(50)).padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                    Text(product.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Built for dependable performance, comfortable movement and memorable time outdoors.", style = MaterialTheme.typography.bodyLarge)
                }
            }
            Button(
                onClick = {
                    viewModel.addProductToCart()
                    cartAdded = true
                },
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(16.dp).height(54.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(stringResource(R.string.wqlno_button_add_to_cart_label))
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary).padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Default.CheckCircle, null, tint = MaterialTheme.colorScheme.surface, modifier = Modifier.size(20.dp))
                Text("  ADDED TO CART", color = MaterialTheme.colorScheme.surface, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
