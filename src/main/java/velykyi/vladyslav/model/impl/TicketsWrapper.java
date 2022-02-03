package velykyi.vladyslav.model.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class TicketsWrapper {

    private List<TicketImpl> list;

    @XmlElement(name = "ticket")
    public List<TicketImpl> getList() {
        return list;
    }

    public void setList(List<TicketImpl> list) {
        this.list = list;
    }
}
