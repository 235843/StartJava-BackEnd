package com.example.trackerplus.Route;

import com.example.trackerplus.RoutePoint.RoutePoint;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "route")
    private Set<RoutePoint> coords;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private double length;
    private double rating;
    private boolean isPublic;

    public Route(){

    }
}
