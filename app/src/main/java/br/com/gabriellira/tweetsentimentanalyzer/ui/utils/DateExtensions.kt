package br.com.gabriellira.tweetsentimentanalyzer.ui.utils

import br.com.gabriellira.tweetsentimentanalyzer.domain.constants.DISPLAY_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Date.toDisplayFormat(): String {
    return SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH).format(this)
}