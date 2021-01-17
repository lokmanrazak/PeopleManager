package com.lokmanrazak.peoplemanager;

import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Person;
import com.lokmanrazak.peoplemanager.resources.PeopleResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PeopleManagerApplication extends Application<PeopleManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new PeopleManagerApplication().run(args);
    }

    private final HibernateBundle<PeopleManagerConfiguration> hibernateBundle =
            new HibernateBundle<>(Person.class) {
                @Override
                public PooledDataSourceFactory getDataSourceFactory(PeopleManagerConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<PeopleManagerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(PeopleManagerConfiguration peopleManagerConfiguration, Environment environment) {
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new PeopleResource(dao));
    }
}
