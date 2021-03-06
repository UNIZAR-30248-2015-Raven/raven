package opdevelopers.raven.calendario;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Daniel on 21/11/2015 based on https://github.com/SundeepK/CompactCalendarView
 */
public class CompactCalendarView extends View {
    private CompactCalendarController compactCalendarController;
    private GestureDetectorCompat gestureDetector;
    private CompactCalendarViewListener listener;
    private boolean shouldScroll = true;

    public interface CompactCalendarViewListener {
        void onDayClick(Date dateClicked);
        void onMonthScroll(Date firstDayOfNewMonth);
    }

    private final GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Date onDateClicked = compactCalendarController.onSingleTapConfirmed(e);
            invalidate();
            if(listener != null && onDateClicked != null){
                listener.onDayClick(onDateClicked);
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return compactCalendarController.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            compactCalendarController.onFling(e1, e2, velocityX, velocityY);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(shouldScroll) {
                compactCalendarController.onScroll(e1, e2, distanceX, distanceY);
                invalidate();
            }
            return true;
        }
    };

    public CompactCalendarView(Context context) {
        this(context, null);
    }

    public CompactCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompactCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        compactCalendarController = new CompactCalendarController(new Paint(), new OverScroller(getContext()),
                new Rect(), attrs, getContext(),  Color.argb(255, 100, 181, 246), Color.argb(255, 0, 0, 0), Color.argb(255, 144, 202, 249));
        gestureDetector = new GestureDetectorCompat(getContext(), gestureListener);
    }

    /*
    Use a custom locale for compact calendar.
     */
    public void setLocale(Locale locale){
        compactCalendarController.setLocale(locale);
        invalidate();
    }

    /*
    Compact calendar will use the locale to determine the abbreviation to use as the day column names.
    The default is to use the default locale and to abbreviate the day names to one character.
    Setting this to true will displace the short weekday string provided by java.
     */
    public void setUseThreeLetterAbbreviation(boolean useThreeLetterAbbreviation){
        compactCalendarController.setUseWeekDayAbbreviation(useThreeLetterAbbreviation);
        invalidate();
    }

    /*
    Will draw the indicator for events as a small dot under the day rather than a circle behind the day.
     */
    public void drawSmallIndicatorForEvents(boolean shouldDrawDaysHeader){
        compactCalendarController.showSmallIndicator(shouldDrawDaysHeader);
    }

    public void setCurrentSelectedDayBackgroundColor(int currentSelectedDayBackgroundColor) {
        compactCalendarController.setCurrentSelectedDayBackgroundColor(currentSelectedDayBackgroundColor);
        invalidate();
    }

    public void setCurrentDayBackgroundColor(int currentDayBackgroundColor) {
        compactCalendarController.setCurrentDayBackgroundColor(currentDayBackgroundColor);
        invalidate();
    }

    public void setListener(CompactCalendarViewListener listener){
        this.listener = listener;
    }

    public Date getFirstDayOfCurrentMonth(){
        return compactCalendarController.getFirstDayOfCurrentMonth();
    }

    /**
     *  Adds an event to be drawn as an indicator in the calendar.
     *  If adding multiple events see {#addEvents(List)}} method.
     * @param event to be added to the calendar
     * @param shouldInvalidate true if the view should invalidate
     */
    public void addEvent(CalendarDayEvent event, boolean shouldInvalidate){
        compactCalendarController.addEvent(event);
        if(shouldInvalidate){
            invalidate();
        }
    }

    /**
     * Removes an event from the calendar.
     * If removing multiple events see {#removeEvents(List)}
     *
     * @param event event to remove from the calendar
     * @param shouldInvalidate true if the view should invalidate
     */
    public void removeEvent(CalendarDayEvent event, boolean shouldInvalidate){
        compactCalendarController.removeEvent(event);
        if(shouldInvalidate){
            invalidate();
        }
    }

    /**
     * Removes all events from the calendar.
     */
    public void removeEvents(){
        compactCalendarController.removeEvents();
    }

    @Override
    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);
        int width = MeasureSpec.getSize(parentWidth);
        int height = MeasureSpec.getSize(parentHeight);
        if(width > 0 && height > 0) {
            compactCalendarController.onMeasure(width, height, getPaddingRight(), getPaddingLeft());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        compactCalendarController.onDraw(canvas);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(compactCalendarController.computeScroll()){
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(compactCalendarController.onTouch(event) && shouldScroll){
            invalidate();
            if(listener != null){
                listener.onMonthScroll(compactCalendarController.getFirstDayOfCurrentMonth());
            }
            return true;
        }
        return gestureDetector.onTouchEvent(event);
    }
}
