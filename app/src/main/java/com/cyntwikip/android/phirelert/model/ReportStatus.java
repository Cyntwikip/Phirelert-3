package com.cyntwikip.android.phirelert.model;

/**
 * Created by Cyntwikip on 7/21/2015.
 */
public enum ReportStatus {
    FOR_VERIFICATION(1),
    RUBBISH_FIRE(2),
    ELECTRICAL_FIRE(3),
    FIRE_UNDER_CONTROL(4),
    FIRE_OUT(5);
    private final int value;

    ReportStatus(int value) { this.value=value; }
    public int getValue() { return value; }

    public static ReportStatus fromInteger(int num) {
        switch(num) {
            case 1:
                return FOR_VERIFICATION;
            case 2:
                return RUBBISH_FIRE;
            case 3:
                return ELECTRICAL_FIRE;
            case 4:
                return FIRE_UNDER_CONTROL;
            case 5:
                return FIRE_OUT;
        }
        return null;
    }

    public static String toString(ReportStatus status) {
        switch(status) {
            case FOR_VERIFICATION:
                return "For Verification";
            case RUBBISH_FIRE:
                return "Rubbish Fire";
            case ELECTRICAL_FIRE:
                return "Electrical Fire";
            case FIRE_UNDER_CONTROL:
                return "Fire Under Control";
            case FIRE_OUT:
                return "Fire Out";
        }
        return "null";
    }

    /*
    CHECK(1),
    EXCLAMATION(2),
    FIRE(3),
    WATER(4);
    private final int value;

    ReportType(int value) { this.value=value; }

    public int getValue() { return value; }

    public static ReportType fromInteger(int num) {
        switch(num) {
            case 1:
                return CHECK;
            case 2:
                return EXCLAMATION;
            case 3:
                return FIRE;
            case 4:
                return WATER;
        }
        return null;
    }
    */

}
