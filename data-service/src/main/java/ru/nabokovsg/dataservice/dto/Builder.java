package ru.nabokovsg.dataservice.dto;

import ru.nabokovsg.dataservice.model.*;
import java.util.Map;

public class Builder {

    private final Map<Long, Organization> organizations;
    private final Map<Long, Employee> employees;
    private final Map<Long, ControlType> controlTypes;
    private final Map<Long, Building> buildings;
    private final Map<Long, ObjectsType> objectsTypes;
    private final Map<Long, Objects> objects;

    private Builder(DataBuilder factory) {
        this.organizations = factory.organizations;
        this.employees = factory.employees;
        this.controlTypes = factory.controlTypes;
        this.buildings = factory.buildings;
        this.objectsTypes = factory.objectsTypes;
        this.objects = factory.objects;
    }

    public Map<Long, Organization> getOrganizations() {
        return organizations;
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public Map<Long, ControlType> getControlTypes() {
        return controlTypes;
    }

    public Map<Long, Building> getBuildings() {
        return buildings;
    }

    public Map<Long, ObjectsType> getObjectsTypes() {
        return objectsTypes;
    }

    public Map<Long, Objects> getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "organizations=" + organizations +
                ", employees=" + employees +
                ", controlTypes=" + controlTypes +
                ", buildings=" + buildings +
                ", objectsTypes=" + objectsTypes +
                '}';
    }

    public static class DataBuilder {

        private Map<Long, Organization> organizations;
        private Map<Long, Employee> employees;
        private Map<Long, ControlType> controlTypes;
        private Map<Long, Building> buildings;
        private Map<Long, ObjectsType> objectsTypes;
        private Map<Long, Objects> objects;

        public DataBuilder organizations(Map<Long, Organization> organizations) {
            this.organizations = organizations;
            return this;
        }

        public DataBuilder employees(Map<Long, Employee> employees) {
            this.employees = employees;
            return this;
        }

        public DataBuilder controlTypes(Map<Long, ControlType> controlTypes) {
            this.controlTypes = controlTypes;
            return this;
        }

        public DataBuilder buildings(Map<Long, Building> buildings) {
            this.buildings = buildings;
            return this;
        }

        public DataBuilder objectsTypes(Map<Long, ObjectsType> objectsTypes) {
            this.objectsTypes = objectsTypes;
            return this;
        }

        public DataBuilder objects(Map<Long, Objects> objects) {
            this.objects = objects;
            return this;
        }

        public Builder build() {
            return new Builder(this);
        }
    }
}