package com.sacnoift.stockmartapp.data.csv

import java.io.InputStream

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}