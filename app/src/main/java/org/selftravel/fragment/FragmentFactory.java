package org.selftravel.fragment;

public class FragmentFactory {
    private FragmentFactory() {
    }

    public static BaseFragment createFragment(int type) {
        BaseFragment fragment = null;
        switch (type) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new DestinationFragment();
                break;
            case 2:
                fragment = new FindFragment();
                break;
            case 3:
                fragment = new SearchFragment();
                break;
            case 4:
                fragment = new MineFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
