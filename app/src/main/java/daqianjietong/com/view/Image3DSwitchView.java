package daqianjietong.com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

@SuppressLint("NewApi")
public class Image3DSwitchView extends ViewGroup{
	 /** 
     * ͼƬ�������ߵĿհ׼�� 
     */  
    public static final int IMAGE_PADDING = 10;  
    private static final int TOUCH_STATE_REST = 0;  
    private static final int TOUCH_STATE_SCROLLING = 1;  
    /** 
     * ��������һ��ͼƬ���ٶ� 
     */  
    private static final int SNAP_VELOCITY = 600;  
    /** 
     * ��ʾ��������һ��ͼƬ������� 
     */  
    private static final int SCROLL_NEXT = 0;  
    /** 
     * ��ʾ��������һ��ͼƬ������� 
     */  
    private static final int SCROLL_PREVIOUS = 1;  
    /** 
     * ��ʾ������ԭͼƬ������� 
     */  
    private static final int SCROLL_BACK = 2;  
    private static Handler handler = new Handler();  
    /** 
     * �ؼ���� 
     */  
    public static int mWidth;
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    /** 
     * ͼƬ��������������ͼƬ��������ʱ�ص�����ӿ� 
     */  
    private OnImageSwitchListener mListener;  
    /** 
     * ��¼��ǰ�Ĵ���״̬ 
     */  
    private int mTouchState = TOUCH_STATE_REST;
    /** 
     * ��¼���ж�Ϊ�����˶�����С����ֵ 
     */  
    private int mTouchSlop;  
    /** 
     * ��¼�ؼ��߶� 
     */  
    private int mHeight;  
    /** 
     * ��¼ÿ��ͼƬ�Ŀ�� 
     */  
    private int mImageWidth;  
    /** 
     * ��¼ͼƬ�������� 
     */  
    private int mCount;  
    /** 
     * ��¼��ǰ��ʾͼƬ������ 
     */  
    private int mCurrentImage;  
    /** 
     * ��¼�ϴδ����ĺ�����ֵ 
     */  
    private float mLastMotionX;  
    /** 
     * �Ƿ�ǿ�����²��� 
     */  
    private boolean forceToRelayout;
    private int[] mItems;  
  
    public Image3DSwitchView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(context);  
    }  
  
    @Override  
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed || forceToRelayout) {
        	
            mCount = getChildCount();  
            // ͼƬ�����������5����Ȼ�޷�������ʾ  
            if (mCount < 5) {
                return;  
            }
            mWidth = getMeasuredWidth();  
            mHeight = getMeasuredHeight();
            // ÿ��ͼƬ�Ŀ���趨Ϊ�ؼ���ȵİٷ�֮��ʮ 
            mImageWidth = (int) (mWidth * 0.6);
            if (mCurrentImage >= 0 && mCurrentImage < mCount) {
                mScroller.abortAnimation();
                setScrollX(0);
                int left = -mImageWidth * 2 + (mWidth - mImageWidth) / 2;
//          �ֱ��ȡÿ��λ����Ӧ����ʾ��ͼƬ�±�  
                int[] items = { getIndexForItem(1), getIndexForItem(2),
                        getIndexForItem(3), getIndexForItem(4),
                        getIndexForItem(5) };
                mItems = items;
                // ͨ��ѭ��Ϊÿ��ͼƬ�趨λ�� 
                for (int i = 0; i < items.length; i++) {
                    Image3DView childView = (Image3DView) getChildAt(items[i]);
                    childView.layout(left + IMAGE_PADDING, 0, left
                            + mImageWidth - IMAGE_PADDING, mHeight);
                    childView.initImageViewBitmap();
                    left = left + mImageWidth;
                }  
                refreshImageShowing(); 
            }
            forceToRelayout = false;
        }
    }  

    @Override  
    public boolean onTouchEvent(MotionEvent event) {
        if (mScroller.isFinished()) {
            if (mVelocityTracker == null) {
                mVelocityTracker = VelocityTracker.obtain();
            }  
            mVelocityTracker.addMovement(event);  
            int action = event.getAction();
            float x = event.getX();  
            switch (action) {  
            case MotionEvent.ACTION_DOWN:
                // ��¼����ʱ�ĺ�����  
                mLastMotionX = x;  
                break;  
            case MotionEvent.ACTION_MOVE:  
                int disX = (int) (mLastMotionX - x);  
                mLastMotionX = x;  
                scrollBy(disX, 0);
                // �������ƶ�ʱˢ��ͼƬ����ʾ״̬
                refreshImageShowing();
                break;  
            case MotionEvent.ACTION_UP:  
                mVelocityTracker.computeCurrentVelocity(1000);  
                int velocityX = (int) mVelocityTracker.getXVelocity();  
                if (shouldScrollToNext(velocityX)) {  
                    // ��������һ��ͼ  
                    scrollToNext();  
                } else if (shouldScrollToPrevious(velocityX)) {  
                    // ��������һ��ͼ  
                    scrollToPrevious();  
                } else {  
                    // �����ص�ǰͼƬ  
                    scrollBack();  
                }  
                if (mVelocityTracker != null) {  
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;  
                }  
                break;  
            }  
        }  
        return true;  
    }  

    /** 
     * ���ݵ�ǰ�Ĵ���״̬�������Ƿ������ӿؼ��Ľ��������� 
     */  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        int action = ev.getAction();  
        if ((action == MotionEvent.ACTION_MOVE)  
                && (mTouchState != TOUCH_STATE_REST)) {  
            return true;  
        }  
        float x = ev.getX();  
        switch (action) {  
        case MotionEvent.ACTION_DOWN:  
            mLastMotionX = x;  
            mTouchState = TOUCH_STATE_REST;
            break;  
        case MotionEvent.ACTION_MOVE:  
            int xDiff = (int) Math.abs(mLastMotionX - x);
            if (xDiff > mTouchSlop) {  
                mTouchState = TOUCH_STATE_SCROLLING;  
            }  
            break;  
        case MotionEvent.ACTION_UP:  
        default:  
            mTouchState = TOUCH_STATE_REST;  
            break;  
        }  
        return mTouchState != TOUCH_STATE_REST;
    }  

    @Override  
    public void computeScroll() {  
        if (mScroller.computeScrollOffset()) {  
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  
            refreshImageShowing();  
            postInvalidate();  
        }  
    }  
  
    /** 
     * ����ͼƬ�����ļ�������ÿ����ͼƬ����ʱ��ص��˽ӿڡ� 
     *  
     * @param listener 
     *            ͼƬ���������� 
     */  
    public void setOnImageSwitchListener(OnImageSwitchListener listener) {  
        mListener = listener;  
    }  
  
    /** 
     * ���õ�ǰ��ʾͼƬ���±꣬ע�������ֵС�������ڵ���ͼƬ����������ͼƬ���޷�������ʾ�� 
     *  
     * @param currentImage 
     *            ͼƬ���±� 
     */  
    public void setCurrentImage(int currentImage) {  
        mCurrentImage = currentImage;  
        requestLayout();  
    }  
  
    /** 
     * ��������һ��ͼƬ�� 
     */  
    public void scrollToNext() {  
        if (mScroller.isFinished()) {  
            int disX = mImageWidth - getScrollX();  
            checkImageSwitchBorder(SCROLL_NEXT);  
            if (mListener != null) {  
                mListener.onImageSwitch(mCurrentImage);  
            }  
            beginScroll(getScrollX(), 0, disX, 0, SCROLL_NEXT);  
        }  
    }  
  
    /** 
     * ��������һ��ͼƬ�� 
     */  
    public void scrollToPrevious() {  
        if (mScroller.isFinished()) {  
            int disX = -mImageWidth - getScrollX();  
            checkImageSwitchBorder(SCROLL_PREVIOUS);  
            if (mListener != null) {  
                mListener.onImageSwitch(mCurrentImage);  
            }  
            beginScroll(getScrollX(), 0, disX, 0, SCROLL_PREVIOUS);  
        }  
    }  
  
    /** 
     * ������ԭͼƬ�� 
     */  
    public void scrollBack() {  
        if (mScroller.isFinished()) {  
            beginScroll(getScrollX(), 0, -getScrollX(), 0, SCROLL_BACK);  
        }  
    }  
  
    /** 
     * ��������ͼƬ�����ͷ��ڴ档 
     */  
    public void clear() {  
        for (int i = 0; i < mCount; i++) {  
            Image3DView childView = (Image3DView) getChildAt(i);  
            childView.recycleBitmap();  
        }  
    }  
  
    /** 
     * �ÿؼ��е�����ͼƬ��ʼ������ 
     */  
    private void beginScroll(int startX, int startY, int dx, int dy,  
            final int action) {  
        int duration = (int) (700f / mImageWidth * Math.abs(dx));  
        mScroller.startScroll(startX, startY, dx, dy, duration);  
        invalidate();  
        handler.postDelayed(new Runnable() {  
            @Override  
            public void run() {  
                if (action == SCROLL_NEXT || action == SCROLL_PREVIOUS) {  
                    forceToRelayout = true;  
                    requestLayout();  
                }  
            }  
        }, duration);  
    }  
  
    /** 
     * ���ݵ�ǰͼƬ���±�ʹ����item���������ж�itemλ����Ӧ����ʾ����ͼƬ�� 
     *  
     * @param item 
     *            ȡֵ��Χ��1-5 
     * @return ��Ӧitemλ����Ӧ����ʾ����ͼƬ�� 
     */  
    private int getIndexForItem(int item) {  
        int index = -1;  
        index = mCurrentImage + item - 3;
        while (index < 0) {
            index = index + mCount;  
        }  
        while (index > mCount - 1) {  
            index = index - mCount;  
        }
        return index;  
    }  

    /** 
     * ˢ������ͼƬ����ʾ״̬��������ǰ����ת�Ƕȡ� 
     */
    private void refreshImageShowing() {
        for (int i = 0; i < mItems.length; i++) {
            Image3DView childView = (Image3DView) getChildAt(mItems[i]);
            childView.setRotateData(i, getScrollX());  
            childView.invalidate();  
        }  
    }  
  
    /**
     * ���ͼƬ�ı߽磬��ֹͼƬ���±곬���涨��Χ�� 
     */
    private void checkImageSwitchBorder(int action) {  
        if (action == SCROLL_NEXT && ++mCurrentImage >= mCount) {  
            mCurrentImage = 0;  
        } else if (action == SCROLL_PREVIOUS && --mCurrentImage < 0) {  
            mCurrentImage = mCount - 1;  
        }  
    }  
  
    /** 
     * �ж��Ƿ�Ӧ�ù�������һ��ͼƬ�� 
     */  
    private boolean shouldScrollToNext(int velocityX) {  
        return velocityX < -SNAP_VELOCITY || getScrollX() > mImageWidth / 2;  
    }  
  
    /** 
     * �ж��Ƿ�Ӧ�ù�������һ��ͼƬ�� 
     */  
    private boolean shouldScrollToPrevious(int velocityX) {  
        return velocityX > SNAP_VELOCITY || getScrollX() < -mImageWidth / 2;  
    }  
  
    /** 
     * ͼƬ�����ļ����� 
     */  
    public interface OnImageSwitchListener {  
  
        /** 
         * ��ͼƬ����ʱ��ص��˷��� 
         *  
         * @param currentImage 
         *            ��ǰͼƬ������ 
         */  
        void onImageSwitch(int currentImage);  
  
    }  
}  
