package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regional_areas")
@IdClass(RegionalArea.class)
public class RegionalArea extends RosterEntity {

    @Id
    @NotNull
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne
    private Region region;

    @Id
    @NotNull
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne
    private Area area;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
