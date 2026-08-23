package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mrzeeshandigital.outdoor.mrzeeshantrail.data.entity.OrderEntity
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.theme.Success
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    val orders = (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    if (orders.isEmpty()) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("No orders yet", style = MaterialTheme.typography.headlineMedium)
            Text("Your reserved gear will appear here.")
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(orders, key = { it.orderNumber }) { order ->
                OrderCard(order)
            }
        }
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(18.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Order #" + order.orderNumber, style = MaterialTheme.typography.titleLarge)
                Text("Completed", color = Success)
            }
            Text(order.timestamp.toLocalDate().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description)
            Text("£%.2f".format(order.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
            Text("Reserved for store collection within 24 hours.")
        }
    }
}
