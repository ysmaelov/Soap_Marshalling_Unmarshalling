import generated.Horse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://adamish.com/example/farm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "form", propOrder = {
        "horse"
})
public class Farm {

        private Horse horse;

        public Horse getHorse ()
        {
            return horse;
        }

        public void setHorse (Horse horse)
        {
            this.horse = horse;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [horse = "+horse+"]";
        }

}
