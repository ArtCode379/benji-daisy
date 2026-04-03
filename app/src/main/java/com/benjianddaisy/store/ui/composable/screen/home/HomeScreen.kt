package com.benjianddaisy.store.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.model.Product
import com.benjianddaisy.store.data.model.ProductCategory
import com.benjianddaisy.store.ui.composable.shared.BJDYSContentWrapper
import com.benjianddaisy.store.ui.composable.shared.BJDYSEmptyView
import com.benjianddaisy.store.ui.state.DataUiState
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSPrimary
import com.benjianddaisy.store.ui.theme.BJDYSSurface
import com.benjianddaisy.store.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
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
        BJDYSContentWrapper(
            dataState = productsState,
            dataPopulated = {
                val products = (productsState as DataUiState.Populated).data
                var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
                val filteredProducts = if (selectedCategory == null) products
                else products.filter { it.category == selectedCategory }
                val heroProducts = products.filter { it.id in listOf(1, 6, 3) }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BJDYSBackground),
                ) {
                    item {
                        HeroCarousel(
                            products = heroProducts,
                            onProductClick = onNavigateToProductDetails,
                        )
                    }
                    item {
                        CategoryChips(
                            selectedCategory = selectedCategory,
                            onCategorySelected = { cat ->
                                selectedCategory = if (selectedCategory == cat) null else cat
                            },
                        )
                    }
                    item {
                        Text(
                            text = if (selectedCategory == null) "ALL PRODUCTS"
                            else stringResource(selectedCategory!!.titleRes).uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = BJDYSMutedText,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        )
                    }
                    val rows = filteredProducts.chunked(2)
                    items(rows) { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            row.forEach { product ->
                                ProductCard(
                                    product = product,
                                    modifier = Modifier.weight(1f),
                                    onClick = { onNavigateToProductDetails(product.id) },
                                )
                            }
                            if (row.size == 1) Spacer(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            },
            dataEmpty = {
                BJDYSEmptyView(
                    primaryText = stringResource(R.string.products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun HeroCarousel(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
) {
    if (products.isEmpty()) return
    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3500)
            val next = (pagerState.currentPage + 1) % products.size
            pagerState.animateScrollToPage(next)
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val product = products[page]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clickable { onProductClick(product.id) }
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC1A1A1A)),
                                startY = 100f,
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp)
                ) {
                    Text(
                        text = stringResource(product.category.titleRes).uppercase(),
                        color = BJDYSAccent,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        letterSpacing = 2.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFFF8F5F2),
                    )
                    Text(
                        text = "£%.2f".format(product.price),
                        style = MaterialTheme.typography.bodyMedium,
                        color = BJDYSAccent,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(products.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (pagerState.currentPage == index) 20.dp else 6.dp, 6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            if (pagerState.currentPage == index) BJDYSAccent
                            else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Composable
private fun CategoryChips(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(ProductCategory.entries) { category ->
            val isSelected = selectedCategory == category
            Box(
                modifier = Modifier
                    .clickable { onCategorySelected(category) }
                    .background(
                        color = if (isSelected) BJDYSPrimary else BJDYSSurface,
                        shape = RoundedCornerShape(0.dp),
                    )
                    .border(
                        width = 1.dp,
                        color = if (isSelected) BJDYSPrimary else BJDYSDivider,
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(
                    text = stringResource(category.titleRes).uppercase(),
                    color = if (isSelected) BJDYSBackground else BJDYSOnSurface,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    letterSpacing = 1.5.sp,
                )
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .clickable { onClick() }
            .border(width = 1.dp, color = BJDYSDivider),
        color = BJDYSSurface,
        shadowElevation = 0.dp,
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = stringResource(product.category.titleRes).uppercase(),
                    color = BJDYSMutedText,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 9.sp,
                    letterSpacing = 1.5.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = BJDYSOnSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "£%.2f".format(product.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = BJDYSAccent,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}
