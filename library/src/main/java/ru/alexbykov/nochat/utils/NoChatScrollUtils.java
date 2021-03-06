package ru.alexbykov.nochat.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatScrollUtils {

    private NoChatScrollUtils() {
    }

    public static boolean isOnBottom(RecyclerView recyclerView, int loadingTriggerThreshold) {
        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        final int firstVisibleItemPosition = getFirstVisibleItemPositionByLayoutManager(recyclerView.getLayoutManager());
        // Check if end of the list is reached (counting threshold) or if there is no items at all
        return (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + loadingTriggerThreshold)
                || totalItemCount == 0;

    }

    public static boolean isOnTop(RecyclerView recyclerView, int loadingTriggerThreshold) {
        final int firstVisiblePosition = getFirstVisibleItemPositionByLayoutManager(recyclerView.getLayoutManager());
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        final View viewByPosition = layoutManager.findViewByPosition(firstVisiblePosition);
        if (viewByPosition != null) {
            final int topPosition = viewByPosition.getTop();
            return (topPosition == 0 && firstVisiblePosition == 0);
        }
        return false;
    }

    private static int getFirstVisibleItemPositionByLayoutManager(RecyclerView.LayoutManager layoutManager) {
        int firstVisibleItemPosition;
        if (layoutManager instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            // https://code.google.com/p/android/issues/detail?id=181461
            if (layoutManager.getChildCount() > 0) {
                firstVisibleItemPosition = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null)[0];
            } else {
                firstVisibleItemPosition = 0;
            }

        } else {
            throw new IllegalStateException("LayoutManager needs to subclass LinearLayoutManager or StaggeredGridLayoutManager");
        }
        return firstVisibleItemPosition;
    }


    public static void scrollToLastVisible(RecyclerView recyclerView) {
        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.getLayoutManager().scrollToPosition(manager.findLastVisibleItemPosition() + 1);
    }


    public static void fullScrollToBottom(RecyclerView.Adapter adapter, RecyclerView recyclerView) {
        recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, adapter.getItemCount() - 1);
    }

    public static void fullScrollToTop(RecyclerView recyclerView) {
        recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, 0);
    }
}
