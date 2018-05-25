package com.example.edvblk.popularmoviesadnd.utils;

import android.content.res.Resources;

import com.example.edvblk.popularmoviesadnd.R;

public class MessagesProviderImpl implements MessagesProvider {
    private final Resources resources;

    public MessagesProviderImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String provideNetworkErrorMessage() {
        return resources.getString(R.string.error_no_network);
    }

    @Override
    public String provideRequestErrorMessage() {
        return resources.getString(R.string.error_network_request_failure);
    }
}
