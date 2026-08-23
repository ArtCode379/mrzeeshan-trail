package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
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
import mrzeeshandigital.outdoor.mrzeeshantrail.data.model.Product
import mrzeeshandigital.outdoor.mrzeeshantrail.data.model.ProductCategory
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.state.DataUiState
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.theme.Accent
import mrzeeshandigital.outdoor.mrzeeshantrail.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    val products = (state as? DataUiState.Populated)?.data.orEmpty()
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val filtered = products.filter { category == null || it.category == category }
    val featured = products.take(4)
    val pager = rememberPagerState(pageCount = { featured.size.coerceAtLeast(1) })
    LaunchedEffect(featured.size) {
        while (featured.isNotEmpty()) {
            delay(4000)
            pager.animateScrollToPage((pager.currentPage + 1) % featured.size)
        }
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text(
                text = "GEAR FOR THE NEXT MILE",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp),
            )
        }
        if (featured.isNotEmpty()) {
            item {
                HorizontalPager(state = pager) { page ->
                    HeroCard(featured[page], onNavigateToProductDetails)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    featured.indices.forEach { index ->
                        Box(
                            Modifier
                                .padding(3.dp)
                                .width(if (index == pager.currentPage) 22.dp else 8.dp)
                                .height(8.dp)
                                .background(
                                    if (index == pager.currentPage) MaterialTheme.colorScheme.secondary
                                    else MaterialTheme.colorScheme.outline,
                                    CircleShape,
                                )
                        )
                    }
                }
            }
        }
        item {
            LazyRow(
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    CategoryChip("All", category == null) { category = null }
                }
                items(ProductCategory.entries) { item ->
                    CategoryChip(stringResource(item.titleRes), category == item) { category = item }
                }
            }
        }
        items(filtered, key = { it.id }) { product ->
            ProductCard(product, onNavigateToProductDetails)
        }
    }
}

@Composable
private fun HeroCard(product: Product, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable { onClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f))
                .padding(16.dp),
        ) {
            Text(product.title, color = MaterialTheme.colorScheme.surface, style = MaterialTheme.typography.titleLarge)
            Text("£%.2f".format(product.price), color = Accent, style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(label) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
private fun ProductCard(product: Product, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().clickable { onClick(product.id) },
        shape = RoundedCornerShape(18.dp),
    ) {
        Row(modifier = Modifier.height(132.dp)) {
            Box(Modifier.width(5.dp).height(132.dp).background(MaterialTheme.colorScheme.primary))
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(132.dp).height(132.dp),
            )
            Column(
                modifier = Modifier.padding(14.dp).weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(product.title, style = MaterialTheme.typography.titleLarge)
                Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
