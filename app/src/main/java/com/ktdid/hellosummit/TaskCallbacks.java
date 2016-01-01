package com.ktdid.hellosummit;

/**
 * An interface for communicating between the async task and the calling activity/fragement
 */
public interface TaskCallbacks<T> {

    void onTaskComplete(T obj);
}
