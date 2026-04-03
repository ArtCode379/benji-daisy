package com.benjianddaisy.store.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.benjianddaisy.store.R
import com.benjianddaisy.store.ui.theme.BJDYSAccent
import com.benjianddaisy.store.ui.theme.BJDYSBackground
import com.benjianddaisy.store.ui.theme.BJDYSDivider
import com.benjianddaisy.store.ui.theme.BJDYSMutedText
import com.benjianddaisy.store.ui.theme.BJDYSOnSurface
import com.benjianddaisy.store.ui.theme.BJDYSSurface

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BJDYSBackground),
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        SettingsSectionHeader(title = "ABOUT")

        SettingsRow(
            label = stringResource(R.string.settings_screen_company_label),
            value = stringResource(R.string.company_name),
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))

        SettingsRow(
            label = stringResource(R.string.settings_screen_version_label),
            value = stringResource(R.string.app_version),
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))

        SettingsLinkRow(
            label = stringResource(R.string.settings_screen_customer_support_label),
            value = stringResource(R.string.customer_support_link),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.customer_support_link)))
                context.startActivity(intent)
            },
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.height(40.dp))

        SettingsSectionHeader(title = "STORE")

        SettingsRow(
            label = "Location",
            value = "UK Showroom",
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))

        SettingsRow(
            label = "Collection Hours",
            value = "Mon–Sat 9am–6pm",
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))

        SettingsRow(
            label = "Collection Time",
            value = "Within 24 hours",
        )
        HorizontalDivider(color = BJDYSDivider, modifier = Modifier.padding(horizontal = 24.dp))
    }
}

@Composable
private fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        color = BJDYSMutedText,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
    )
}

@Composable
private fun SettingsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BJDYSSurface)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSOnSurface,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSMutedText,
        )
    }
}

@Composable
private fun SettingsLinkRow(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BJDYSSurface)
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BJDYSOnSurface,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = BJDYSAccent,
        )
        Icon(
            painter = painterResource(R.drawable.link_svgrepo_com),
            contentDescription = null,
            tint = BJDYSAccent,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(14.dp),
        )
    }
}
