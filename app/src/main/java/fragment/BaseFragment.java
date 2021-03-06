package fragment;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import spotify.com.spotifysample.R;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class BaseFragment extends DialogFragment {
    public static int NO_ANIMATION = -1;

    public static void addToBackStack(final FragmentManager manager,
                                      BaseFragment fragment, boolean defaultAnimation) {
        if (defaultAnimation) {
            addToBackStack(manager, fragment);
        } else {
            replace(manager, R.id.fragment_base_container, fragment, 0, 0, 0, 0, true);
        }
    }

    public static void addToBackStack(final FragmentManager manager,
                                      BaseFragment fragment) {
        addToBackStack(manager, fragment, R.id.fragment_base_container);
    }

    public static void addToBackStack(final FragmentManager manager,
                                      BaseFragment fragment, int target) {
        replace(manager, target, fragment, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, true);
    }


    public static void replace(FragmentManager fragmentManager, int targetId,
                               BaseFragment fragment, int enter, int exit, int popEnter,
                               int popExit, boolean isAddToBackStack) {

        FragmentTransactionManager.replace(fragmentManager, targetId,
                fragment,
                enter, exit, popEnter, popExit, isAddToBackStack);
    }

    public static void popBackStack(FragmentManager manager) {
        if (manager != null) {
            try {

                manager.popBackStack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void popToHome(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            popBackStackTo(fragmentManager,
                    fragmentManager.getBackStackEntryAt(1));
        }
    }

    public static void popBackStackTo(FragmentManager manager,
                                      FragmentManager.BackStackEntry entry) {
        FragmentTransactionManager.popBackStackTo(manager, entry,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }



    public interface OnFragmentInteractionListener {
        public void showDrawerToggle(boolean showDrawerToggle);
    }

}
