package other

import java.text.SimpleDateFormat
import java.util.*

fun commonAnnotation(provider: ArmsPluginTemplateProviderImpl) = """
    
/**
 * 
 * Author by weioule.
 * Date on ${SimpleDateFormat("yyyy/MM/dd").format(Date(System.currentTimeMillis()))}.
 */
""".trimIndent()

val armsAnnotation = """
/**
 * 
 * Author by weioule.
 * Date on ${SimpleDateFormat("yyyy/MM/dd").format(Date(System.currentTimeMillis()))}.
 */
""".trimIndent()