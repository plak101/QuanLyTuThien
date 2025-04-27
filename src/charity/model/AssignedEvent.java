package charity.model;

import java.util.Date;

public class AssignedEvent {
    private int id;
    private int organizationId;
    private int eventId;
    private Date assignedDate;

    public AssignedEvent() {
    }

    public AssignedEvent(int organizationid, int eventId, Date assignedDate) {
        this.organizationId = organizationId;
        this.eventId = eventId;
        this.assignedDate = assignedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }
    
    
}
