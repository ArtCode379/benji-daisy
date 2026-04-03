package com.benjianddaisy.store.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.entity.OrderEntity
import com.benjianddaisy.store.ui.composable.shared.BJDYSContentWrapper
import com.benjianddaisy.store.ui.composable.shared.BJDYSEmptyView
import com.benjianddaisy.store.ui.state.DataUiState
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