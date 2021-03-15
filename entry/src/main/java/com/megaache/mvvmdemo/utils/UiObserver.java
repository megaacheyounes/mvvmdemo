package com.megaache.mvvmdemo.utils;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.abilityjet.activedata.DataObserver;
import ohos.app.dispatcher.TaskDispatcher;

public abstract class UiObserver<T> extends DataObserver<T> {

    private TaskDispatcher uiTaskDispatcher;

    public UiObserver(Ability baseAbilitySlice) {
        setLifecycle(baseAbilitySlice.getLifecycle());
        uiTaskDispatcher = baseAbilitySlice.getUITaskDispatcher();
    }

    @Override
    public void onChanged(T t) {
        uiTaskDispatcher.asyncDispatch(() -> onValueChanged(t));
    }

    public abstract void onValueChanged(T t);
}
