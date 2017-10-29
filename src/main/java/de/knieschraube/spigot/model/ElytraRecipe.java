package de.knieschraube.spigot.model;

import org.bukkit.Material;

import java.util.Objects;


public class ElytraRecipe {

    private Material wingsMaterial;
    private Material baseMaterial;
    private String displayName;
    private double velocity;

    private ElytraRecipe(Builder builder) {
        wingsMaterial = builder.wingsMaterial;
        baseMaterial = builder.baseMaterial;
        displayName = builder.displayName;
        velocity = builder.velocity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Material wingsMaterial;
        private Material baseMaterial;
        private String displayName;
        private double velocity;

        private Builder() {
        }

        public Builder wingsMaterial(Material val) {
            wingsMaterial = val;
            return this;
        }

        public Builder baseMaterial(Material val) {
            baseMaterial = val;
            return this;
        }

        public Builder displayName(String val) {
            displayName = val;
            return this;
        }

        public Builder velocity(double val) {
            velocity = val;
            return this;
        }

        public ElytraRecipe build() {
            return new ElytraRecipe(this);
        }
    }

    public Material getWingsMaterial() {
        return wingsMaterial;
    }

    public Material getBaseMaterial() {
        return baseMaterial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getVelocity() {
        return velocity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElytraRecipe that = (ElytraRecipe) o;
        return Double.compare(that.velocity, velocity) == 0 &&
                wingsMaterial == that.wingsMaterial &&
                baseMaterial == that.baseMaterial &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wingsMaterial, baseMaterial, displayName, velocity);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
