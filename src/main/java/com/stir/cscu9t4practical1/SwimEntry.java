package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {
    private String where;

    public SwimEntry(String n, int d, int m, int y, int h, int min, int s, float dist, String where) {
        super(n, d, m, y, h, min, s, dist);
        this.where = where;
    }

    public String getWhere() {
        if (where.equals("pool")) {
            return "in a pool";
        } else {
            return where;
        }
    }

    @Override
    public String getEntry() {
        return getName() + " swam " + getDistance() + " km " + where + " in " +
                getHour() + ":" + getMin() + ":" + getSec() + " on " +
                getDay() + "/" + getMonth() + "/" + getYear() + "\n";
    }
}
