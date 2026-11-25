package com.equinoxdrive.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF0A0D12)
private val Panel = Color(0xFF151B24)
private val Accent = Color(0xFF35C6B0)
private val Muted = Color(0xFF8B98A8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(
                background = Background,
                surface = Panel,
                primary = Accent,
                onBackground = Color.White,
                onSurface = Color.White
            )) {
                DashboardScreen()
            }
        }
    }
}

@Composable
private fun DashboardScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Background) {
        Row(
            modifier = Modifier.fillMaxSize().padding(22.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Column(
                modifier = Modifier.weight(1.35f).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("EQUINOX", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Text("DRIVE  •  VEHICLE DASHBOARD", color = Muted, fontSize = 11.sp, letterSpacing = 2.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("—", color = Color.White, fontSize = 92.sp, fontWeight = FontWeight.Light)
                    Text("km/h", color = Accent, fontSize = 16.sp, letterSpacing = 3.sp)
                    Spacer(Modifier.height(14.dp))
                    Text("SPEED DATA NOT CONNECTED", color = Muted, fontSize = 10.sp, letterSpacing = 1.5.sp)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SmallTile("BATTERY", "— V")
                    SmallTile("OUTSIDE", "— °C")
                    SmallTile("CANBUS", "NOT LINKED")
                }
            }
            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                    MetricTile("FUEL LEFT", "— L", "Fuel level", Modifier.weight(1f))
                    MetricTile("RANGE", "— km", "Estimated distance", Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                    MetricTile("INSTANT", "— L/100", "Instant consumption", Modifier.weight(1f))
                    MetricTile("AVERAGE", "— L/100", "Average consumption", Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ActionTile("MUSIC", Modifier.weight(1f))
                    ActionTile("EQUALIZER", Modifier.weight(1f))
                    ActionTile("SETTINGS", Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun SmallTile(label: String, value: String) {
    Column(
        modifier = Modifier.background(Panel, RoundedCornerShape(14.dp)).padding(horizontal = 14.dp, vertical = 11.dp)
    ) {
        Text(label, color = Muted, fontSize = 9.sp, letterSpacing = 1.sp)
        Spacer(Modifier.height(5.dp))
        Text(value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun MetricTile(label: String, value: String, detail: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(Panel, RoundedCornerShape(18.dp)).padding(18.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(label, color = Accent, fontSize = 10.sp, letterSpacing = 1.5.sp)
        Spacer(Modifier.height(8.dp))
        Text(value, color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Text(detail, color = Muted, fontSize = 10.sp)
    }
}

@Composable
private fun ActionTile(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(58.dp).background(Panel, RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
    }
}
