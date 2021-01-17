package com.lokmanrazak.peoplemanager;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PeopleManagerApplication extends Application<PeopleManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new PeopleManagerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PeopleManagerConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(PeopleManagerConfiguration peopleManagerConfiguration, Environment environment) {

    }
}
