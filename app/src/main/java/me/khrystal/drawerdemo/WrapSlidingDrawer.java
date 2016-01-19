package me.khrystal.drawerdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SlidingDrawer;

/**
 * Created by ASUS on 2016/1/15.
 */
public class WrapSlidingDrawer extends SlidingDrawer {
    private boolean mVertical;
    private int mTopOffset;
    private ViewGroup mHandleLayout;
    private final Rect mHitRect = new Rect();

    public WrapSlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        int orientation = attrs.getAttributeIntValue("android", "orientation", ORIENTATION_VERTICAL);
        mTopOffset = attrs.getAttributeIntValue("android", "topOffset", 0);
        mVertical = (orientation == SlidingDrawer.ORIENTATION_VERTICAL);
    }

    public WrapSlidingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        int orientation = attrs.getAttributeIntValue("android", "orientation", ORIENTATION_VERTICAL);
        mTopOffset = attrs.getAttributeIntValue("android", "topOffset", 0);
        mVertical = (orientation == SlidingDrawer.ORIENTATION_VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View handle = getHandle();

        if (handle instanceof ViewGroup) {
            mHandleLayout = (ViewGroup) handle;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mHandleLayout != null) {
            int childCount = mHandleLayout.getChildCount();
            int handleClickX = (int)(event.getX() - mHandleLayout.getX());
            int handleClickY = (int)(event.getY() - mHandleLayout.getY());

            Rect hitRect = mHitRect;

            for (int i=0;i<childCount;i++) {
                View childView = mHandleLayout.getChildAt(i);
                childView.getHitRect(hitRect);

                if (hitRect.contains(handleClickX, handleClickY)) {
                    return false;
                }
            }
        }

        return super.onInterceptTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize =  MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize =  MeasureSpec.getSize(heightMeasureSpec);

        final View handle = getHandle();
        final View content = getContent();
        measureChild(handle, widthMeasureSpec, heightMeasureSpec);

        if (mVertical) {
            int height = heightSpecSize - handle.getMeasuredHeight() - mTopOffset;
            content.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightSpecMode));
            heightSpecSize = handle.getMeasuredHeight() + mTopOffset + content.getMeasuredHeight();
            widthSpecSize = content.getMeasuredWidth();
            if (handle.getMeasuredWidth() > widthSpecSize) widthSpecSize = handle.getMeasuredWidth();
        }
        else {
            int width = widthSpecSize - handle.getMeasuredWidth() - mTopOffset;
            getContent().measure(MeasureSpec.makeMeasureSpec(width, widthSpecMode), heightMeasureSpec);
            widthSpecSize = handle.getMeasuredWidth() + mTopOffset + content.getMeasuredWidth();
            heightSpecSize = content.getMeasuredHeight();
            if (handle.getMeasuredHeight() > heightSpecSize) heightSpecSize = handle.getMeasuredHeight();
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }
}
