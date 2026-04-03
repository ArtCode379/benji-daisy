package com.benjianddaisy.store.ui.composable.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.benjianddaisy.store.R
import com.benjianddaisy.store.data.entity.OrderEntity
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSPrimary
import com.benjianddaisy.store.ui.theme.BJDYSSurface

@Composable
fun CheckoutDialog(
    order: OrderEntity,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = { onConfirm() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    ) {
        Surface(
            shape = RoundedCornerShape(0.dp),
            color = BJDYSSurface,
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "✓",
                    fontSize = 40.sp,
                    color = BJDYSAccent,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.checkout_dialog_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = BJDYSOnSurface,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = BJDYSDivider)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.checkout_dialog_order_number, order.orderNumber),
                    style = MaterialTheme.typography.bodyMedium,
                    color = BJDYSMutedText,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.checkout_dialog_processing_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = BJDYSMutedText,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { onConfirm() },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BJDYSPrimary,
                        contentColor = BJDYSBackground,
                    ),
                ) {
                    Text(
                        text = "VIEW MY ORDERS",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 2.sp,
                    )
                }
            }
        }
    }
}
