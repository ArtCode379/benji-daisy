package com.benjianddaisy.store.ui.composable.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benjianddaisy.store.R
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BJDYSBackground)
            .padding(24.dp),
    ) {
        Text(
            text = "BENJI & DAISY",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 3.sp,
            color = BJDYSAccent,
            modifier = Modifier.padding(bottom = 4.dp),
        )
        Text(
            text = "Fine Flooring & Rugs",
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSMutedText,
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "ABOUT",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
            color = BJDYSMutedText,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        SettingsRow(
            label = stringResource(R.string.settings_screen_company_label),
            value = stringResource(R.string.company_name),
        )
        HorizontalDivider(color = BJDYSDivider)

        SettingsRow(
            label = stringResource(R.string.settings_screen_version_label),
            value = stringResource(R.string.app_version),
        )
        HorizontalDivider(color = BJDYSDivider)

        SettingsRow(
            label = stringResource(R.string.settings_screen_customer_support_label),
            value = stringResource(R.string.customer_support_link),
        )
        HorizontalDivider(color = BJDYSDivider)
    }
}

@Composable
private fun SettingsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSOnSurface,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSMutedText,
        )
    }
}
