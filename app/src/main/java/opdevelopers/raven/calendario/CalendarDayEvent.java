package opdevelopers.raven.calendario;

import opdevelopers.raven.Event;

/**
 * Created by Daniel on 21/11/2015 based on https://github.com/SundeepK/CompactCalendarView
 */
public class CalendarDayEvent {

    private final long timeInMillis;
    private final int color;
    private final Event event;

    public CalendarDayEvent(final long timeInMillis, final int color, final Event event) {
        this.timeInMillis = timeInMillis;
        this.color = color;
        this.event = event;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public int getColor() {
        return color;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarDayEvent event = (CalendarDayEvent) o;

        if (color != event.color) return false;
        if (timeInMillis != event.timeInMillis) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (timeInMillis ^ (timeInMillis >>> 32));
        result = 31 * result + color;
        return result;
    }

    @Override
    public String
    toString() {
        return "CalendarDayEvent{" +
                "timeInMillis=" + timeInMillis +
                ", color=" + color +
                ", id = " + event.getId() +
                ", email = " + event.getEmail() +
                ", mensaje = " + event.getMensaje() +
                ", date = " + event.getDate() +
                ", time = " + event.getTime() +
                ", periodicidad = " + event.getPeriodicidad() +
                '}';
    }
}
