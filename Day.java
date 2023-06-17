public enum Day {
    Sunday(1),
    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6),
    Saturday(0);

    private int dayLabel;
    public int getDayLabel() {
        return dayLabel;
    }


    Day(int dayLabel){
        this.dayLabel = dayLabel;
    }


}
