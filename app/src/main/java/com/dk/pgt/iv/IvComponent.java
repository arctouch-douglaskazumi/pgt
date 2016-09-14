package com.dk.pgt.iv;

import com.dk.pgt.data.DataComponent;
import com.dk.pgt.evolve.ServiceScope;

import dagger.Component;

/**
 * Created by douglaskazumi on 9/6/16.
 */
@ServiceScope
@Component(dependencies = DataComponent.class, modules = IvModule.class)
public interface IvComponent {
    void inject(IvView evolveView);
}
