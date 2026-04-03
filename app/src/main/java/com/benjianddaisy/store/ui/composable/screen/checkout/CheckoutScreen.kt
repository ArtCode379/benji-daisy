package com.benjianddaisy.store.ui.composable.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.entity.OrderEntity
import com.benjianddaisy.store.ui.state.DataUiState
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSPrimary
import com.benjianddaisy.store.ui.theme.BJDYSSurface
import com.benjianddaisy.store.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalidState by viewModel.emailInvalidState.collectAsStateWithLifecycle()

    val isButtonEnabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotEmpty() &&
                    viewModel.customerLastName.isNotEmpty() &&
                    viewModel.customerEmail.isNotEmpty()
        }
    }

    if (orderState is DataUiState.Populated) {
        CheckoutDialog(
            order = (orderState as DataUiState.Populated<OrderEntity>).data,
            onConfirm = onNavigateToOrdersScreen
        )
    }

    CheckoutContent(
        customerFirstName = viewModel.customerFirstName,
        customerLastName = viewModel.customerLastName,
        customerEmail = viewModel.customerEmail,
        isEmailInvalid = emailInvalidState,
        modifier = modifier,
        focusManager = focusManager,
        isButtonEnabled = isButtonEnabled,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPlaceOrderButtonClick = viewModel::placeOrder
    )
}

@Composable
private fun CheckoutContent(
    customerFirstName: String,
    customerLastName: String,
    customerEmail: String,
    isEmailInvalid: Boolean,
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    isButtonEnabled: Boolean,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPlaceOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BJDYSBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .navigationBarsPadding()
    ) {
        Text(
            text = "YOUR DETAILS",
            style = MaterialTheme.typography.labelMedium,
            color = BJDYSMutedText,
        )
        Spacer(modifier = Modifier.height(24.dp))

        CheckoutTextField(
            input = customerFirstName,
            onInputChange = onFirstNameChanged,
            labelText = stringResource(R.string.checkout_text_field_first_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        )
        Spacer(modifier = Modifier.height(16.dp))

        CheckoutTextField(
            input = customerLastName,
            onInputChange = onLastNameChanged,
            labelText = stringResource(R.string.checkout_text_field_last_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        )
        Spacer(modifier = Modifier.height(16.dp))

        CheckoutTextField(
            input = customerEmail,
            onInputChange = onEmailChanged,
            labelText = stringResource(R.string.checkout_text_field_email),
            modifier = Modifier.fillMaxWidth(),
            isError = isEmailInvalid,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (isEmailInvalid) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Please enter a valid email address",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        HorizontalDivider(color = BJDYSDivider)
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "COLLECTION",
            style = MaterialTheme.typography.labelMedium,
            color = BJDYSMutedText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your order will be ready to collect from our showroom within 24 hours of confirmation.",
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSOnSurface,
            lineHeight = 22.sp,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onPlaceOrderButtonClick() },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BJDYSAccent,
                contentColor = BJDYSPrimary,
                disabledContainerColor = BJDYSDivider,
                disabledContentColor = BJDYSMutedText,
            ),
        ) {
            Text(
                text = stringResource(R.string.button_confirm_order_label).uppercase(),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 2.sp,
            )
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = labelText,
                style = MaterialTheme.typography.titleSmall,
            )
        },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        shape = RoundedCornerShape(0.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BJDYSSurface,
            unfocusedContainerColor = BJDYSSurface,
            focusedTextColor = BJDYSOnSurface,
            unfocusedTextColor = BJDYSOnSurface,
            focusedBorderColor = BJDYSPrimary,
            unfocusedBorderColor = BJDYSDivider,
            focusedLabelColor = BJDYSPrimary,
            unfocusedLabelColor = BJDYSMutedText,
            cursorColor = BJDYSPrimary,
        ),
    )
}
