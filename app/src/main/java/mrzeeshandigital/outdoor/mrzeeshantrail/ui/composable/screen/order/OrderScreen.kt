package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mrzeeshandigital.outdoor.mrzeeshantrail.R
import mrzeeshandigital.outdoor.mrzeeshantrail.data.entity.OrderEntity
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.shared.WQLNOContentWrapper
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.shared.WQLNOEmptyView
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        WQLNOContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                WQLNOEmptyView(
                    primaryText = stringResource(R.string.wqlno_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}