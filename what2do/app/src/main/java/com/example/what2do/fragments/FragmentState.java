package com.example.what2do.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentState extends ViewModel {
    private final MutableLiveData<Integer> selectedItem = new MutableLiveData<Integer>();

    public void setState(Integer state) {
        selectedItem.setValue(state);
    }

    public LiveData<Integer> getState() {
        return selectedItem;
    }
}
