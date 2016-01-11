package com.ane.framework.Base.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ane.framework.Base.interFace.FragmentToThis;
import com.ane.framework.Base.interFace.UIWritCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zengcanxiang on 2015/12/31.
 */
public abstract class BaseFragment extends Fragment implements FragmentToThis,UIWritCode {

    @Override
    public Bundle getBundle() {
        return getArguments();
    }

    @Override
    public void setBundle(Bundle toBundle, Class<?> parentActivity) {
        Method method = null;
        try {
            method = parentActivity.getMethod("getFragmentBundle", Bundle.class);
            method.invoke(parentActivity, toBundle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View initView(LayoutInflater inflaterint, int createViewId, ViewGroup container) {
        View view = inflaterint.inflate(createViewId, container, false);
        return view;
    }

    @Override
    public View getView(int viewId) {
        View view=getActivity().findViewById(viewId);
        return view;
    }
    @Override
    public void inItActivityWritCode() {
        findViews();
        setViewsContent();
        setViewsListener();
        disposeBusiness();
    }

}
