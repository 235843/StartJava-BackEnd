package com.example.trackerplus.RoutePoint;

import com.example.trackerplus.Route.Route;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class RoutePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Route route;

    private double geoWidth;

    private double geoHeight;

    public RoutePoint(){

    }
}
