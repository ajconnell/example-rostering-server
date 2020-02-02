package com.wackywallaby.rosterexample.model;

public enum Proficiency {

    TRAINING(1),
    BASIC(2),
    COMPETENT(3),
    ADVANCED(4),
    EXPERT(5);

    private int proficiencyStars;

    Proficiency(int proficiencyStars) {
        this.proficiencyStars = proficiencyStars;
    }

    public int bestMatchValue() {
        return this.proficiencyStars;
    }

}
