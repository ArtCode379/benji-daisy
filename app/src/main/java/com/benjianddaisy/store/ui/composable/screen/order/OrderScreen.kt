package com.benjianddaisy.store.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.entity.OrderEntity
import com.benjianddaisy.store.ui.composable.shared.BJDYSContentWrapper
import com.benjianddaisy.store.ui.composable.shared.BJDYSEmptyView
import com.benjianddaisy.store.ui.state.DataUiState
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSSurface
import com.benjianddaisy.store.ui.viewmodel.OrderViewModel
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
        BJDYSContentWrapper(
            dataState = ordersState,
            dataPopulated = {
                val orders = (ordersState as DataUiState.Populated).data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BJDYSBackground),
                ) {
                    item {
                        Text(
                            text = "${orders.size} ORDER${if (orders.size != 1) "S" else ""}",
                            style = MaterialTheme.typography.labelMedium,
                            color = BJDYSMutedText,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        )
                    }
                    items(orders, key = { it.orderNumber }) { order ->
                        OrderCard(order = order)
                        HorizontalDivider(color = BJDYSDivider)
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            },
            dataEmpty = {
                BJDYSEmptyView(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(BJDYSSurface),
        color = BJDYSSurface,
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber),
                    style = MaterialTheme.typography.titleSmall,
                    color = BJDYSOnSurface,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "£%.2f".format(order.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = BJDYSAccent,
                    fontWeight = FontWeight.Medium,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                style = MaterialTheme.typography.bodySmall,
                color = BJDYSMutedText,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.customerEmail,
                style = MaterialTheme.typography.bodySmall,
                color = BJDYSMutedText,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .background(BJDYSBackground)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "READY TO COLLECT",
                    color = BJDYSAccent,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 9.sp,
                    letterSpacing = 1.5.sp,
                )
            }
        }
    }
}
