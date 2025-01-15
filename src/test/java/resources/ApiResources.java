package resources;

public enum ApiResources {
    AddplaceApi("/maps/api/place/add/json"),
    GetPlaceApi("/maps/api/place/get/json"),
    DeletePlaceApi("/maps/api/place/delete/json");
    private String resource;

    ApiResources(String resource) {
        this.resource = resource;
    }
    public String getResource(){
        return this.resource;
    }
}
