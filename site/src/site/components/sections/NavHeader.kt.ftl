package ${package}.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.theme.SilkTheme
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import org.jetbrains.compose.web.css.px

private val NAV_ITEM_PADDING = Modifier.padding(0.px, 15.px)

@Composable
private fun NavLink(path: String, text: String) {
    Link(
        path,
        text,
        NAV_ITEM_PADDING,
        // Intentionally invert the header colors (here, "primary" instead of "onPrimary")
        color = SilkTheme.palette.primary,
        decorationLine = TextDecorationLine.None
    )
}

@Composable
fun NavHeader() {
    var colorMode by rememberColorMode()
    val palette = SilkTheme.palette
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.px)
            // Intentionally invert the header colors (here, setting the background to "onPrimary" instead of "primary")
            .background(palette.onPrimary),
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavLink("/", "HOME")
            NavLink("/about", "ABOUT")
            NavLink("/markdown", "MARKDOWN")
            Spacer()
            Button(
                onClick = { colorMode = colorMode.opposite() },
                NAV_ITEM_PADDING.clip(Circle())
            ) {
                Box(Modifier.padding(4.px)) {
                    when (colorMode) {
                        ColorMode.LIGHT -> FaSun()
                        ColorMode.DARK -> FaMoon()
                    }
                }
            }
        }
    }
}