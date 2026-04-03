package com.benjianddaisy.store.ui.composable.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benjianddaisy.store.R
import com.benjianddaisy.store.ui.composable.shared.BJDYSContentWrapper
import com.benjianddaisy.store.ui.composable.shared.BJDYSEmptyView
import com.benjianddaisy.store.ui.state.CartItemUiState
import com.benjianddaisy.store.ui.state.DataUiState
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSPrimary
import com.benjianddaisy.store.ui.theme.BJDYSSurface
import com.benjianddaisy.store.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = { itemId -> viewModel.incrementProductInCart(itemId) },
        onMinusItemClick = { itemId -> viewModel.decrementItemInCart(itemId) },
        onDeleteItemClick = { itemId -> viewModel.deleteFromCart(itemId) },
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: DataUiState<List<CartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onDeleteItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {
        BJDYSContentWrapper(
            dataState = cartItemsState,
            dataPopulated = {
                val items = (cartItemsState as DataUiState.Populated).data
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BJDYSBackground)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 160.dp),
                    ) {
                        item {
                            Text(
                                text = "${items.size} ITEM${if (items.size != 1) "S" else ""} IN YOUR CART",
                                style = MaterialTheme.typography.labelMedium,
                                color = BJDYSMutedText,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                            )
                        }
                        items(items, key = { it.productId }) { item ->
                            CartItemRow(
                                item = item,
                                onPlus = { onPlusItemClick(item.productId) },
                                onMinus = { onMinusItemClick(item.productId) },
                                onDelete = { onDeleteItemClick(item.productId) },
                            )
                            HorizontalDivider(color = BJDYSDivider)
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(BJDYSSurface)
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .navigationBarsPadding()
                    ) {
                        HorizontalDivider(color = BJDYSDivider)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "TOTAL",
                                style = MaterialTheme.typography.labelMedium,
                                color = BJDYSMutedText,
                            )
                            Text(
                                text = "£%.2f".format(totalPrice),
                                style = MaterialTheme.typography.titleMedium,
                                color = BJDYSOnSurface,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { onCompleteOrderButtonClick() },
                            modifier = Modifier.fillMaxWidth().height(52.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BJDYSPrimary,
                                contentColor = BJDYSBackground,
                            ),
                        ) {
                            Text(
                                text = "PROCEED TO CHECKOUT",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                letterSpacing = 2.sp,
                            )
                        }
                    }
                }
            },
            dataEmpty = {
                BJDYSEmptyView(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun CartItemRow(
    item: CartItemUiState,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BJDYSSurface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = item.productImageRes ?: R.drawable.ic_launcher_background),
            contentDescription = item.productTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .border(1.dp, BJDYSDivider),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.productTitle,
                style = MaterialTheme.typography.titleSmall,
                color = BJDYSOnSurface,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "£%.2f".format(item.productPrice),
                style = MaterialTheme.typography.bodyMedium,
                color = BJDYSAccent,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { onMinus() },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.minus_svgrepo_com),
                        contentDescription = stringResource(R.string.decrease_quantity_icon_description),
                        tint = BJDYSOnSurface,
                        modifier = Modifier.size(16.dp),
                    )
                }
                Text(
                    text = "${item.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BJDYSOnSurface,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                IconButton(
                    onClick = { onPlus() },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.plus_svgrepo_com),
                        contentDescription = stringResource(R.string.increase_quantity_icon_description),
                        tint = BJDYSOnSurface,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }
        IconButton(onClick = { onDelete() }) {
            Icon(
                painter = painterResource(R.drawable.trash_svgrepo_com),
                contentDescription = stringResource(R.string.delete_item_icon_description),
                tint = BJDYSMutedText,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}
