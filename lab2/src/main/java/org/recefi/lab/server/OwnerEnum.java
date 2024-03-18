package org.recefi.lab.server;

public enum OwnerEnum {
    BLACK,  // 0
    WHITE,  // 1
    NONE;  // 2

    public static OwnerEnum fromInt(int ownerId) {
        switch (ownerId) {
            case 0:
                return BLACK;
            case 1:
                return WHITE;
            default:
                return NONE;
        }
    }
}
