package net.transitdata.pelias_java_sdk;

import net.transitdata.pelias_java_sdk.gson.Feature;
import net.transitdata.pelias_java_sdk.gson.Geometry;
import net.transitdata.pelias_java_sdk.gson.Properties;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SimpleFeatureTest {
    @Test
    public void getAddress_shouldReturnLabelTextWithoutName() throws Exception {
        assertThat(getTestSimpleFeature().address()).isEqualTo("Manhattan, NY");
    }

    @Test
    public void fromFeature_shouldConstructFromFeatureObject() throws Exception {
        assertThat(SimpleFeature.fromFeature(getTestFeature())).isEqualTo(getTestSimpleFeature());
    }

    @Test
    public void isAddress_shouldReturnTrueIfResultIsFromAddressLayer() throws Exception {
        Feature feature = getTestFeature();
        feature.properties.layer = "address";
        SimpleFeature simpleFeature = SimpleFeature.fromFeature(feature);
        assertThat(simpleFeature.isAddress()).isTrue();
    }

    @Test
    public void isAddress_shouldReturnFalseIfResultIsFromAnotherLayer() throws Exception {
        Feature feature = getTestFeature();
        feature.properties.layer = "locality";
        SimpleFeature simpleFeature = SimpleFeature.fromFeature(feature);
        assertThat(simpleFeature.isAddress()).isFalse();
    }

    public static SimpleFeature getTestSimpleFeature() {
        return getTestSimpleFeature("Test SimpleFeature");
    }

    public static SimpleFeature getTestSimpleFeature(String name) {
        return SimpleFeature.builder()
                .lat(1.0)
                .lng(1.0)
                .id("123")
                .gid("osm:venue:123")
                .name(name)
                .country("United States")
                .countryAbbr("USA")
                .region("New York")
                .regionAbbr("NY")
                .county("New York County")
                .localAdmin("Manhattan")
                .locality("New York")
                .neighborhood("Koreatown")
                .label("Test SimpleFeature, Manhattan, NY")
                .layer("locality")
                .build();
    }

    public static Feature getTestFeature() {
        final Properties properties = new Properties();
        properties.id = "123";
        properties.gid = "osm:venue:123";
        properties.name = "Test SimpleFeature";
        properties.country = "United States";
        properties.country_a = "USA";
        properties.region = "New York";
        properties.region_a = "NY";
        properties.county = "New York County";
        properties.localadmin = "Manhattan";
        properties.locality = "New York";
        properties.neighbourhood = "Koreatown";
        properties.label = "Test SimpleFeature, Manhattan, NY";
        properties.layer = "locality";

        final Geometry geometry = new Geometry();
        geometry.coordinates.add(1.0);
        geometry.coordinates.add(1.0);

        final Feature feature = new Feature();
        feature.properties = properties;
        feature.geometry = geometry;
        return feature;
    }
}
