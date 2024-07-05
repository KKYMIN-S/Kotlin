package Class_Date

import java.util.*
class Date {
    private var year = 0
    private var month = 0
    private var day = 0

    internal enum class WEEKDAY {
        SUN, MON, TUE, WED, THR, FRI, SAT
    }

    internal enum class MONTH {
        Dummy, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    val weekDayName = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    val weekDayNameShort = arrayOf("SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT")
    val monthName = arrayOf(
        "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    )

    constructor() { //default constructor
        year = 0; month = 0; day = 0
    }

    constructor(y: Int, m: Int, d: Int) {
        setDate(y, m, d)
    }

    fun getMonth(): Int {
        return month
    }

    fun getDay(): Int {
        return day
    }

    private fun isValidDate(y: Int, m: Int, d: Int): Boolean {
        val days_month = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        if (this.isLeapYear(y)) days_month[2] = 29
        return if (m >= 1 || m <= 12 || d >= 1 || d <= days_month[m])
            true
        else {
            println("Illegal date! (month = %d, day = %d)".format(m, d))
            false
        }
    }

    private fun setDate(y: Int, m: Int, d: Int) {
        if (isValidDate(y, m, d)) {
            year = y
            month = m
            day = d
        }
        else {
            println("Illegal date! (%d, %d, %d) ==> Program aborted.".format(y, m, d))
        }
    }

    fun getYearDay(year: Int, month: Int, day: Int): Int {
        val days_month = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var yearDay = 0
        for (m in 1 until month)
            yearDay += days_month[m]
        yearDay += day
        if (month > 2 && isLeapYear(year))
            yearDay += 1
        return yearDay
    }

    fun getElapsedDaysFromAD010101(): Int {
        var elpsDay = 0
        elpsDay = getElapsedDaysFromAD010101(this)
        return elpsDay
    }

    private fun getElapsedDaysFromAD010101(d: Date): Int {
        val yearDay: Int
        var elpsDay = 0
        for (y in 1 until d.year) {
            elpsDay += if (isLeapYear(y)) 366 else 365
        }
        yearDay = getYearDay(d.year, d.month, d.day)
        elpsDay += yearDay
        return elpsDay
    }

    fun getWeekDay(d: Date): Int {
        val weekDay_AD010101 = 1
        var yearDay: Int
        val weekDay: Int
        var elapsedDays = 0
        elapsedDays = getElapsedDaysFromAD010101(d)
        weekDay = (elapsedDays + weekDay_AD010101 - 1) % 7
        return weekDay
    }

    fun inputDate() {
        val y: Int
        val m: Int
        val d: Int
        val cin = Scanner(System.`in`)
        print("Enter date in year month day : ")
        y = cin.nextInt()
        m = cin.nextInt()
        d = cin.nextInt()

        if (isValidDate(y, m, d))
        {
            year = y
            month = m
            day = d
        }
        else {
            print("Illegal date! Program aborted.\n")
        }
    }

    fun isLeapYear(y: Int): Boolean {
        return if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) true
        else false
    }

    private fun isLeapYear(): Boolean {
        return isLeapYear(year)
    }

    override fun toString(): String {
        var yearDay = 0
        val weekDay: Int
        var str: String = ""
        if (month >= 1 && month <= 12)
            str += String.format("%s".format(monthName[month]))
        str += String.format(" %d, %d".format(day, year))
        yearDay = getYearDay(year, month, day)
        if (yearDay < 1 || yearDay > 366) {
            str += String.format("Error in calculation of yearDay : %d".format(yearDay))
        }
        else {
            weekDay = getWeekDay(this)
            str += String.format(" (%s)".format(weekDayName[weekDay]))
        }
        return str
    }
}