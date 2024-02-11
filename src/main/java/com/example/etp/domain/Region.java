package com.example.etp.domain;

/**
 * Enumeration of the different regions of the islands in the Philippenes.
 * <p>
 * Created by Jeffrey Asante
 */
public enum Region {
    South_Boracay("South Borocau"), Central_Boracay("Central Boracay"), East_Bohol("East Bohol"),
    North_Bohol("North Bohol"), South_Siquijor("South Siquijor"), North_Palawan("North Palawan");
    private String label;

    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel) {
        for (Region thisParticularRegion : Region.values()) {
            if (thisParticularRegion.label.equalsIgnoreCase(byLabel))
                return thisParticularRegion;
        }
        return null;
    }
}
