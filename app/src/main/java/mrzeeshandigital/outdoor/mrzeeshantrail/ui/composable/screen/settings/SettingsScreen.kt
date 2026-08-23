package mrzeeshandigital.outdoor.mrzeeshantrail.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val supportUrl = "https://mrzeeshan-digital.surf"
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Card(shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("MrZeeshan Trail", style = MaterialTheme.typography.titleLarge)
                Text("MR ZEESHAN DIGITAL LTD")
                Text("Version 1.0")
                Text("Outdoor gear selected for movement, fitness and time beyond the city.")
            }
        }
        Text("Support", style = MaterialTheme.typography.headlineMedium)
        Button(
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(supportUrl)))
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Icon(Icons.Default.OpenInNew, null)
            Text("  Customer Support")
        }
        Text(supportUrl, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
