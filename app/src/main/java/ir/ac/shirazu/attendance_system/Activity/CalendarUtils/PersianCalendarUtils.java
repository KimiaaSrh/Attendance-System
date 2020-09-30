package ir.ac.shirazu.attendance_system.Activity.CalendarUtils;

/**
 * algorithms for converting Julian days to the Persian calendar, and vice versa
 * are adopted from <a
 * href="casema.nl/couprie/calmath/persian/index.html">couprie.nl</a> written in
 * VB. The algorithms is not exactly the same as its original. I've done some
 * minor changes in the sake of performances and corrected some bugs.
 *
 * @author Morteza contact: <a
 *         href="mailto:Mortezaadi@gmail.com">Mortezaadi@gmail.com</a>
 * @version 1.0
 *
 */
public class PersianCalendarUtils {
    public static long ceil(double double1, double double2) {
        return (long) (double1 - double2 * Math.floor(double1 / double2));
    }

    public static long persianToJulian(long year, int month, int day) {
        return 365L * ((ceil(year - 474L, 2820D) + 474L) - 1L) + ((long) Math.floor((682L * (ceil(year - 474L, 2820D) + 474L) - 110L) / 2816D)) + (PersianCalendarConstants.PERSIAN_EPOCH - 1L) + 1029983L
                * ((long) Math.floor((year - 474L) / 2820D)) + (month < 7 ? 31 * month : 30 * month + 6) + day;
    }


    /**
     * Calculate whether current year is Leap year in persian or not
     *
     * @return boolean
     */
    public static boolean isPersianLeapYear(int persianYear) {
        return PersianCalendarUtils.ceil((38D + (PersianCalendarUtils.ceil(persianYear - 474L, 2820L) + 474L)) * 682D, 2816D) < 682L;
    }

    public static long julianToPersian(long julianDate) {
        long persianEpochInJulian = julianDate - persianToJulian(475L, 0, 1);
        long cyear = ceil(persianEpochInJulian, 1029983D);
        long ycycle = cyear != 1029982L ? ((long) Math.floor((2816D * (double) cyear + 1031337D) / 1028522D)) : 2820L;
        long year = 474L + 2820L * ((long) Math.floor(persianEpochInJulian / 1029983D)) + ycycle;
        long aux = (1L + julianDate) - persianToJulian(year, 0, 1);
        int month = (int) (aux > 186L ? Math.ceil((double) (aux - 6L) / 30D) - 1 : Math.ceil((double) aux / 31D) - 1);
        int day = (int) (julianDate - (persianToJulian(year, month, 1) - 1L));
        return (year << 16) | (month << 8) | day;
    }
}
