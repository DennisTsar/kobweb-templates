package todo

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.cursor
import com.varabyte.kobweb.compose.css.fontStyle
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.textAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import org.jetbrains.compose.web.css.*

const val BORDER_COLOR = "#eaeaea"
const val INTERACT_COLOR = "#0070f3"

// The standard Web Compose way of defining styles
// Note: It is hoped as Kobweb improves this section will be less and less necessary
object TodoStyleSheet : StyleSheet() {
    init {
        "body" style {
            fontFamily(
                "-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
                "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
            )
        }

        // Common styles for all todo widgets
        ".todo" style {
            width(85.percent)
            height(5.cssRem)
            property("border", "1px solid $BORDER_COLOR")
            borderRadius(10.px)
            property("transition", "color 0.15s ease, border-color 0.15s ease")
            textDecoration("none")
        }

        // Styles for the bordered, outer container (the form component has an inner and outer layer)
        ".todoContainer" style {
            display(DisplayStyle.Flex)
            textAlign(TextAlign.Left)
            alignItems(AlignItems.Center)
            margin(0.5.cssRem)
            property("border", "1px solid $BORDER_COLOR")
        }

        // Styles for text parts of todo widgets
        ".todoText" style {
            // We use "A" tags for accessibility, but we want our colors to come from our container
            property("color", "inherit")
            padding(1.5.cssRem)
            fontSize(1.25.cssRem)
        }

        // Styles for the input tag which handles user input
        ".todoInput" style {
            border(0.px)
            background("transparent") // So the input doesn't render over its parent container's border
            width(100.percent)
        }

        ".todoInput::placeholder" style {
            fontStyle(FontStyle.Italic)
        }

        ".todoClickable:hover" style {
            property("color", INTERACT_COLOR)
            property("border-color", INTERACT_COLOR)
            textDecoration("line-through")
            cursor(Cursor.Pointer)
        }

        "footer" style {
            width(100.percent)
            height(100.px)
            property("border-top", "1px solid $BORDER_COLOR")
            fontSize(1.5.cssRem)

            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }
}

// The Kobweb way of defining styles, useable for composables provided by the Kobweb library (anything that takes a
// `Modifier` as its first argument)
object Styles {
    // "styleModifier" is an escape hatch for when Kobweb doesn't (yet) have the HTML style modifier that you
    // need. Allows you to define styles the traditional Web Compose way. You can use "attrModifier" too, which has
    // extra functionality, like specifying event listeners.
    val Title = Modifier
        .lineHeight(1.15)
        .fontSize(4.cssRem)
        .margin(top = 0.4.em, bottom = 0.6.em)
        .styleModifier {
            fontWeight(FontWeight.Bold)
        }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Style(TodoStyleSheet)
    SilkApp {
        Surface(Modifier.width(100.vw).height(100.vh)) {
            content()
        }
    }
}