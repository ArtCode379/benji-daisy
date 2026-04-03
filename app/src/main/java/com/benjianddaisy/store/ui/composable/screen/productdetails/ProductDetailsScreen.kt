package com.benjianddaisy.store.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.model.Product
import com.benjianddaisy.store.ui.composable.shared.BJDYSContentWrapper
import com.benjianddaisy.store.ui.composable.shared.BJDYSEmptyView
import com.benjianddaisy.store.ui.state.DataUiState
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSPrimary
import com.benjianddaisy.store.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier) {
        BJDYSContentWrapper(
            dataState = productState,
            dataPopulated = {
                val product = (productState as DataUiState.Populated).data
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .background(BJDYSBackground)
                            .padding(bottom = 100.dp)
                    ) {
                        Image(
                            painter = painterResource(id = product.imageRes),
                            contentDescription = stringResource(R.string.product_image_description),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                        )
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text(
                                text = stringResource(product.category.titleRes).uppercase(),
                                color = BJDYSMutedText,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                letterSpacing = 2.sp,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = BJDYSOnSurface,
                                fontSize = 22.sp,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "£%.2f".format(product.price),
                                style = MaterialTheme.typography.headlineSmall,
                                color = BJDYSAccent,
                                fontWeight = FontWeight.Normal,
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            HorizontalDivider(color = BJDYSDivider)
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "DESCRIPTION",
                                color = BJDYSMutedText,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                letterSpacing = 2.sp,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = product.description,
                                style = MaterialTheme.typography.bodyLarge,
                                color = BJDYSOnSurface,
                                lineHeight = 26.sp,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(BJDYSBackground)
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .navigationBarsPadding()
                    ) {
                        HorizontalDivider(color = BJDYSDivider)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { onAddToCart() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BJDYSAccent,
                                contentColor = BJDYSPrimary,
                            ),
                        ) {
                            Text(
                                text = stringResource(R.string.button_add_to_cart_label).uppercase(),
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
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
