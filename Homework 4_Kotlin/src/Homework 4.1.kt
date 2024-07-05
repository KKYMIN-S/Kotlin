/* Homework 4.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 4.
 * class Date
 */

import Class_Date.Date
import java.time.LocalDate
import java.time.ZoneId

fun main(args: Array<String>) {
    val AD010101 = Date(1, 1,1)
    val year: Int
    val month: Int
    val day: Int
    val daysToChristmas: Int
    val now = LocalDate.now(ZoneId.of("Asia/Seoul"))
    val month_str = now.month.toString()
    year = now.year
    month = now.monthValue
    day = now.dayOfMonth
    val newYearDay = Date(year, 1, 1)
    val today = Date(year, month, day)
    val christmas = Date(year, 12, 25)
    println("AD Jan. 1, 1 is " + AD010101)
    println("Today is " + today)
    println("New year's day of this year was " + newYearDay)
    println("Christmas of this year is " + christmas)
    if (today.getMonth() === christmas.getMonth() &&
        today.getDay() === christmas.getDay()
    ) {
        println("Today is Christmas! Happy Christmas to you all !!")
    } else {
        println("Sorry, today is not Christmas!")
        daysToChristmas = christmas.getElapsedDaysFromAD010101() - today.getElapsedDaysFromAD010101()
        println(" => You must wait %d day(s) to Christmas !".format(daysToChristmas)) }
}