package velykyi.vladyslav.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import velykyi.vladyslav.model.impl.TicketImpl;
import velykyi.vladyslav.model.impl.TicketsWrapper;

import java.util.Collections;

@Configuration
public class UtilsConfigurator {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(TicketImpl.class, TicketsWrapper.class);
        jaxb2Marshaller.setMarshallerProperties(Collections.<String, Object>singletonMap(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                                                                                          Boolean.TRUE));
        return jaxb2Marshaller;
    }

}
