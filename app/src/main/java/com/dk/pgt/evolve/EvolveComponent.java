package com.dk.pgt.evolve;

import com.dk.pgt.data.DataComponent;

import dagger.Component;


/**
 * Created by douglaskazumi on 8/23/16.
 */

@ServiceScope
@Component(dependencies = DataComponent.class, modules = EvolveModule.class)
public interface EvolveComponent {
    void inject(EvolveView evolveView);
}

