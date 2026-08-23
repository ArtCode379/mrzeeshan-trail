package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mrzeeshandigital.outdoor.mrzeeshantrail.R
import mrzeeshandigital.outdoor.mrzeeshantrail.data.model.Product
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.shared.WQLNOContentWrapper
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.shared.WQLNOEmptyView
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    Column(modifier = modifier) {

        WQLNOContentWrapper(
            dataState = productsState,

            dataPopulated = {
                val data = (productsState as DataUiState.Populated).data
            },

            dataEmpty = {
                WQLNOEmptyView(
                    primaryText = stringResource(R.string.wqlno_products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}