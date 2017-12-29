package top.bdwuzhou.mytime.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author wuzhou
 * @date 2017/12/27
 */

@Data
public class BaiduCityNameResult {
    private Location location;
    @SerializedName("formatted_address")
    private String formattedAddress;
    private String business;
    private AddressComponent addressComponent;
    @SerializedName("sematic_description")
    private String sematicDes;
    private int cityCode;
    private List<?> pois;
    private List<?> roads;
    private List<PoiRegion> mPoiRegions;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getSematicDes() {
        return sematicDes;
    }

    public void setSematicDes(String sematicDes) {
        this.sematicDes = sematicDes;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public List<?> getPois() {
        return pois;
    }

    public void setPois(List<?> pois) {
        this.pois = pois;
    }

    public List<?> getRoads() {
        return roads;
    }

    public void setRoads(List<?> roads) {
        this.roads = roads;
    }

    public List<PoiRegion> getPoiRegions() {
        return mPoiRegions;
    }

    public void setPoiRegions(List<PoiRegion> poiRegions) {
        mPoiRegions = poiRegions;
    }

    @Data
    public static class Location {
        private double lng;
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    @Data
    public static class AddressComponent {
        private String country;
        @SerializedName("country_code")
        private int countryCode;
        @SerializedName("country_code_iso")
        private String countryCodeIso;
        private String province;
        private String city;
        @SerializedName("city_level")
        private int cityLevel;
        private String district;
        private String town;
        @SerializedName("adcode")
        private String adCode;
        private String street;
        @SerializedName("street_number")
        private String streetNumber;
        private String direction;
        private String distance;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(int countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryCodeIso() {
            return countryCodeIso;
        }

        public void setCountryCodeIso(String countryCodeIso) {
            this.countryCodeIso = countryCodeIso;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityLevel() {
            return cityLevel;
        }

        public void setCityLevel(int cityLevel) {
            this.cityLevel = cityLevel;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getAdCode() {
            return adCode;
        }

        public void setAdCode(String adCode) {
            this.adCode = adCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }

    @Data
    public static class PoiRegion {
        @SerializedName("direction_desc")
        private String directionDesc;
        private String name;
        private String tag;

        public String getDirectionDesc() {
            return directionDesc;
        }

        public void setDirectionDesc(String directionDesc) {
            this.directionDesc = directionDesc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
