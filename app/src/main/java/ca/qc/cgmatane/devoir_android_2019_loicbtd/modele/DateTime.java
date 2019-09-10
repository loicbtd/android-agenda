package ca.qc.cgmatane.devoir_android_2019_loicbtd.modele;

public class DateTime {

    private int year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private byte second;

    public DateTime(int year, byte month, byte day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public DateTime(int year, byte month, byte day, byte hour, byte minute, byte second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinute() {
        return minute;
    }

    public void setMinute(byte minute) {
        this.minute = minute;
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return year + "-" +
                month + "-" +
                day + " " +
                hour + ":" +
                minute + ":" +
                second + ".00";
    }
}

