package com.ecommerce.product.about;


public class AboutRepresentation {
    private String buildNumber;
    private String buildTime;
    private String deployTime;
    private String gitRevision;
    private String gitBranch;
    private String environment;

    public AboutRepresentation(String buildNumber, String buildTime, String deployTime, String gitRevision, String gitBranch, String environment) {
        this.buildNumber = buildNumber;
        this.buildTime = buildTime;
        this.deployTime = deployTime;
        this.gitRevision = gitRevision;
        this.gitBranch = gitBranch;
        this.environment = environment;
    }

}
